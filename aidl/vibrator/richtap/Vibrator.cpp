/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#include "Vibrator.h"

#include <cutils/properties.h>
#include <inttypes.h>
#include <log/log.h>

#include <algorithm>
#include <cstring>
#include <map>
#include <thread>

#include "aac_vibra_function.h"

#define RICHTAP_LIGHT_STRENGTH 69
#define RICHTAP_MEDIUM_STRENGTH 89
#define RICHTAP_STRONG_STRENGTH 99

namespace aidl {
namespace android {
namespace hardware {
namespace vibrator {

namespace {

constexpr int32_t kComposeDelayMaxMs = 1000;
constexpr int32_t kComposeSizeMax = 10;

enum class HapticProfile { RICHTAP, CRISP, GENTLE };

struct PrimitiveInfo {
    Effect effect;
    int32_t strengthOffset;
    int32_t durationMs;
};

const std::map<CompositePrimitive, PrimitiveInfo> kPrimitiveMap = {
        {CompositePrimitive::NOOP, {Effect::CLICK, 0, 0}},
        {CompositePrimitive::CLICK, {Effect::CLICK, 0, 20}},
        {CompositePrimitive::THUD, {Effect::THUD, 4, 30}},
        {CompositePrimitive::SPIN, {Effect::POP, 6, 25}},
        {CompositePrimitive::QUICK_RISE, {Effect::CLICK, 4, 20}},
        {CompositePrimitive::SLOW_RISE, {Effect::TICK, 8, 25}},
        {CompositePrimitive::QUICK_FALL, {Effect::THUD, 6, 25}},
        {CompositePrimitive::LIGHT_TICK, {Effect::TICK, 14, 15}},
        {CompositePrimitive::LOW_TICK, {Effect::TICK, 20, 15}},
};

HapticProfile getHapticProfile() {
    char profile[PROPERTY_VALUE_MAX];

    property_get("persist.vendor.haptic_profile", profile, "richtap");

    if (strcmp(profile, "crisp") == 0) return HapticProfile::CRISP;
    if (strcmp(profile, "gentle") == 0) return HapticProfile::GENTLE;

    return HapticProfile::RICHTAP;
}

int32_t adjustStrength(int32_t strength) {
    switch (getHapticProfile()) {
        case HapticProfile::CRISP:
            // Uniformly punchy: lift everything into the top of the range
            strength = std::max(strength + 10, 88);
            break;
        case HapticProfile::GENTLE:
            strength = strength * 3 / 4;
            break;
        case HapticProfile::RICHTAP:
            break;
    }

    return std::clamp(strength, 1, 99);
}

int32_t primitiveStrength(float scale, int32_t strengthOffset) {
    // AAC strength is perceptually compressed near the top of its 0-99
    // range (EffectStrength::LIGHT already maps to 69), so keep primitives
    // inside the tactile 55-99 band and differentiate them with offsets
    int32_t strength = 55 + static_cast<int32_t>(44.0f * scale + 0.5f) - strengthOffset;

    return adjustStrength(strength);
}

}  // namespace

Vibrator::Vibrator() {
    uint32_t deviceType = 0;

    int32_t ret = aac_vibra_init(&deviceType);
    if (ret) {
        ALOGE("AAC init failed: %d\n", ret);
        return;
    }

    aac_vibra_looper_start();

    ALOGI("AAC init success: %u\n", deviceType);
}

ndk::ScopedAStatus Vibrator::getCapabilities(int32_t* _aidl_return) {
    *_aidl_return = IVibrator::CAP_ON_CALLBACK | IVibrator::CAP_PERFORM_CALLBACK |
                    IVibrator::CAP_AMPLITUDE_CONTROL | IVibrator::CAP_COMPOSE_EFFECTS;

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::off() {
    int32_t ret = aac_vibra_off();
    if (ret) {
        ALOGE("AAC off failed: %d\n", ret);
        return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_SERVICE_SPECIFIC));
    }

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::on(int32_t timeoutMs,
                                const std::shared_ptr<IVibratorCallback>& callback) {
    int32_t ret = aac_vibra_looper_on(timeoutMs);
    if (ret < 0) {
        ALOGE("AAC on failed: %d\n", ret);
        return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_SERVICE_SPECIFIC));
    }

    if (callback != nullptr) {
        std::thread([=] {
            usleep(ret * 1000);
            callback->onComplete();
        }).detach();
    }

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::perform(Effect effect, EffectStrength es,
                                     const std::shared_ptr<IVibratorCallback>& callback,
                                     int32_t* _aidl_return) {
    int32_t strength;

    if (effect < Effect::CLICK || effect > Effect::HEAVY_CLICK)
        return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));

    switch (es) {
        case EffectStrength::LIGHT:
            strength = RICHTAP_LIGHT_STRENGTH;
            break;
        case EffectStrength::MEDIUM:
            strength = RICHTAP_MEDIUM_STRENGTH;
            break;
        case EffectStrength::STRONG:
            strength = RICHTAP_STRONG_STRENGTH;
            break;
        default:
            return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
    }

    int32_t ret = aac_vibra_looper_prebaked_effect(static_cast<uint32_t>(effect),
                                                   adjustStrength(strength));
    if (ret < 0) {
        ALOGE("AAC perform failed: %d\n", ret);
        return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_SERVICE_SPECIFIC));
    }

    if (callback != nullptr) {
        std::thread([=] {
            usleep(ret * 1000);
            callback->onComplete();
        }).detach();
    }

    *_aidl_return = ret;

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::getSupportedEffects(std::vector<Effect>* _aidl_return) {
    *_aidl_return = {Effect::CLICK, Effect::DOUBLE_CLICK, Effect::TICK,
                     Effect::THUD,  Effect::POP,          Effect::HEAVY_CLICK};

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::setAmplitude(float amplitude) {
    uint8_t tmp = (uint8_t)(amplitude * 0xff);

    int32_t ret = aac_vibra_setAmplitude(tmp);
    if (ret) {
        ALOGE("AAC set amplitude failed: %d\n", ret);
        return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_SERVICE_SPECIFIC));
    }

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::setExternalControl(bool enabled __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getCompositionDelayMax(int32_t* maxDelayMs) {
    *maxDelayMs = kComposeDelayMaxMs;

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::getCompositionSizeMax(int32_t* maxSize) {
    *maxSize = kComposeSizeMax;

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::getSupportedPrimitives(std::vector<CompositePrimitive>* supported) {
    for (const auto& [primitive, info] : kPrimitiveMap) {
        supported->push_back(primitive);
    }

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::getPrimitiveDuration(CompositePrimitive primitive,
                                                  int32_t* durationMs) {
    auto it = kPrimitiveMap.find(primitive);
    if (it == kPrimitiveMap.end())
        return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_ILLEGAL_ARGUMENT));

    *durationMs = it->second.durationMs;

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::compose(const std::vector<CompositeEffect>& composite,
                                     const std::shared_ptr<IVibratorCallback>& callback) {
    if (composite.empty() || composite.size() > kComposeSizeMax)
        return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_ILLEGAL_ARGUMENT));

    for (const auto& e : composite) {
        if (e.delayMs < 0 || e.delayMs > kComposeDelayMaxMs)
            return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_ILLEGAL_ARGUMENT));
        if (e.scale < 0.0f || e.scale > 1.0f)
            return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_ILLEGAL_ARGUMENT));
        if (kPrimitiveMap.find(e.primitive) == kPrimitiveMap.end())
            return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_ILLEGAL_ARGUMENT));
    }

    std::thread([=] {
        for (const auto& e : composite) {
            if (e.delayMs > 0) usleep(e.delayMs * 1000);

            if (e.primitive == CompositePrimitive::NOOP) continue;

            const PrimitiveInfo& info = kPrimitiveMap.at(e.primitive);
            int32_t ret = aac_vibra_looper_prebaked_effect(
                    static_cast<uint32_t>(info.effect),
                    primitiveStrength(e.scale, info.strengthOffset));
            if (ret < 0) {
                ALOGE("AAC compose primitive %d failed: %d\n", static_cast<int32_t>(e.primitive),
                      ret);
                continue;
            }

            if (ret > 0) usleep(ret * 1000);
        }

        if (callback != nullptr) callback->onComplete();
    }).detach();

    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus Vibrator::getSupportedAlwaysOnEffects(
        std::vector<Effect>* _aidl_return __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::alwaysOnEnable(int32_t id __unused, Effect effect __unused,
                                            EffectStrength strength __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::alwaysOnDisable(int32_t id __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getResonantFrequency(float* resonantFreqHz __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getQFactor(float* qFactor __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getFrequencyResolution(float* freqResolutionHz __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getFrequencyMinimum(float* freqMinimumHz __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getBandwidthAmplitudeMap(std::vector<float>* _aidl_return __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getPwlePrimitiveDurationMax(int32_t* durationMs __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getPwleCompositionSizeMax(int32_t* maxSize __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::getSupportedBraking(std::vector<Braking>* supported __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

ndk::ScopedAStatus Vibrator::composePwle(const std::vector<PrimitivePwle>& composite __unused,
                                         const std::shared_ptr<IVibratorCallback>& callback
                                                 __unused) {
    return ndk::ScopedAStatus(AStatus_fromExceptionCode(EX_UNSUPPORTED_OPERATION));
}

}  // namespace vibrator
}  // namespace hardware
}  // namespace android
}  // namespace aidl
