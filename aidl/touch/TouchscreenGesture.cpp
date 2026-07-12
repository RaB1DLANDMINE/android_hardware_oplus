/*
 * SPDX-FileCopyrightText: 2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#define LOG_TAG "vendor.lineage.touch-service.oplus"

#include <android-base/logging.h>
#include <android-base/file.h>
#include <android-base/stringprintf.h>
#include <android-base/strings.h>

#include <OplusTouchConstants.h>
#include <TouchscreenGestureConfig.h>

using ::android::base::ReadFileToString;
using ::android::base::StringPrintf;
using ::android::base::Trim;
using ::android::base::WriteStringToFile;

namespace {

constexpr const char* kGestureEnableIndepPath = "/proc/touchpanel/double_tap_enable_indep";

}  // anonymous namespace

namespace aidl {
namespace vendor {
namespace lineage {
namespace touch {

TouchscreenGesture::TouchscreenGesture(std::shared_ptr<IOplusTouch> oplusTouch)
    : mOplusTouch(std::move(oplusTouch)) {}

ndk::ScopedAStatus TouchscreenGesture::getSupportedGestures(std::vector<Gesture>* _aidl_return) {
    std::vector<Gesture> gestures;

    for (const auto& [id, name] : kGestureNames) {
        if (kSupportedGestures & (1 << id)) {
            gestures.push_back({static_cast<int>(gestures.size()), name, kGestureStartKey + id});
        }
    }

    *_aidl_return = gestures;
    return ndk::ScopedAStatus::ok();
}

ndk::ScopedAStatus TouchscreenGesture::setGestureEnabled(const Gesture& gesture, bool enabled) {
    int contents = 0;
    const int gestureBit = 1 << (gesture.keycode - kGestureStartKey);

    if (gestureBit == OplusTouchConstants::DOUBLE_TAP_GESTURE) {
        LOG(INFO) << "DT2W debug: ignoring touchscreen gesture write for Settings-owned "
                  << gesture.name;
        return ndk::ScopedAStatus::ok();
    }

    if (std::string tmp; mOplusTouch) {
        mOplusTouch->touchReadNodeFile(OplusTouchConstants::DEFAULT_TP_IC_ID,
                                       OplusTouchConstants::DOUBLE_TAP_INDEP_NODE, &tmp);
        LOG(INFO) << "DT2W debug: read OPlus node "
                  << OplusTouchConstants::DOUBLE_TAP_INDEP_NODE << " raw=" << tmp
                  << " gesture=" << gesture.name << " keycode=" << gesture.keycode
                  << " enabled=" << enabled;
        contents = std::stoi(tmp, nullptr, 16);
    } else if (ReadFileToString(kGestureEnableIndepPath, &tmp)) {
        LOG(INFO) << "DT2W debug: read proc node " << kGestureEnableIndepPath
                  << " raw=" << Trim(tmp) << " gesture=" << gesture.name
                  << " keycode=" << gesture.keycode << " enabled=" << enabled;
        contents = std::stoi(Trim(tmp), nullptr, 16);
    } else {
        LOG(ERROR) << "DT2W debug: failed to read gesture enable node";
        return ndk::ScopedAStatus::fromExceptionCode(EX_UNSUPPORTED_OPERATION);
    }

    if (enabled) {
        contents |= gestureBit;
    } else {
        contents &= ~gestureBit;
    }
    contents |= OplusTouchConstants::DOUBLE_TAP_GESTURE;

    const std::string contentsHex = StringPrintf("%x", contents);
    const std::string contentsDec = std::to_string(contents);
    LOG(INFO) << "DT2W debug: write contentsDec=" << contents
              << " contentsHex=" << contentsHex << " gestureBit=" << gestureBit;

    if (mOplusTouch) {
        int enableResult = 0;
        int indepResult = 0;
        auto enableStatus = mOplusTouch->touchWriteNodeFile(
                OplusTouchConstants::DEFAULT_TP_IC_ID, OplusTouchConstants::DOUBLE_TAP_ENABLE_NODE,
                "1", &enableResult);
        auto indepStatus = mOplusTouch->touchWriteNodeFile(
                OplusTouchConstants::DEFAULT_TP_IC_ID, OplusTouchConstants::DOUBLE_TAP_INDEP_NODE,
                contentsDec, &indepResult);
        LOG(INFO) << "DT2W debug: sync write enableStatus=" << enableStatus.getDescription()
                  << " enableResult=" << enableResult
                  << " indepStatus=" << indepStatus.getDescription()
                  << " indepResult=" << indepResult;
        if (std::string tmp;
            mOplusTouch->touchReadNodeFile(OplusTouchConstants::DEFAULT_TP_IC_ID,
                                           OplusTouchConstants::DOUBLE_TAP_INDEP_NODE, &tmp)
                    .isOk()) {
            LOG(INFO) << "DT2W debug: readback OPlus node "
                      << OplusTouchConstants::DOUBLE_TAP_INDEP_NODE << " raw=" << tmp;
        }
    } else if (!WriteStringToFile(contentsHex, kGestureEnableIndepPath, true)) {
        return ndk::ScopedAStatus::fromExceptionCode(EX_UNSUPPORTED_OPERATION);
    }

    return ndk::ScopedAStatus::ok();
}

}  // namespace touch
}  // namespace lineage
}  // namespace vendor
}  // namespace aidl
