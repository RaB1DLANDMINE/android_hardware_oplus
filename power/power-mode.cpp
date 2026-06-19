/*
 * SPDX-FileCopyrightText: 2024-2025 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

#include <aidl/android/hardware/power/BnPower.h>
#include <aidl/vendor/oplus/hardware/touch/IOplusTouch.h>

#include <android-base/logging.h>
#include <android-base/stringprintf.h>
#include <android/binder_manager.h>

#include <OplusTouchConstants.h>

using aidl::android::hardware::power::Mode;
using aidl::vendor::oplus::hardware::touch::IOplusTouch;
using android::base::StringPrintf;

#ifdef LIBPERFMGR_EXT
namespace aidl::google::hardware::power::impl::pixel {
#else
namespace aidl::android::hardware::power::impl {
#endif

bool isDeviceSpecificModeSupported(Mode type, bool* _aidl_return) {
    switch (type) {
        case Mode::DOUBLE_TAP_TO_WAKE:
            *_aidl_return = true;
            return true;
        default:
            return false;
    }
}

bool setDeviceSpecificMode(Mode type, bool enabled) {
    switch (type) {
        case Mode::DOUBLE_TAP_TO_WAKE: {
            std::string tmp;
            int contents = 0;

            const std::string instance = std::string() + IOplusTouch::descriptor + "/default";
            std::shared_ptr<IOplusTouch> oplusTouch = IOplusTouch::fromBinder(
                    ndk::SpAIBinder(AServiceManager_waitForService(instance.c_str())));
            LOG(INFO) << "Power mode: " << toString(type) << " isDoubleTapEnabled: " << enabled;

            oplusTouch->touchReadNodeFile(OplusTouchConstants::DEFAULT_TP_IC_ID,
                                          OplusTouchConstants::DOUBLE_TAP_INDEP_NODE, &tmp);
            LOG(INFO) << "DT2W debug: read OPlus node "
                      << OplusTouchConstants::DOUBLE_TAP_INDEP_NODE << " raw=" << tmp;
            contents = std::stoi(tmp, nullptr, 16);

            if (enabled) {
                contents |= OplusTouchConstants::DOUBLE_TAP_GESTURE;
            } else {
                contents &= ~OplusTouchConstants::DOUBLE_TAP_GESTURE;
            }

            const std::string contentsHex = StringPrintf("%x", contents);
            const std::string contentsDec = std::to_string(contents);
            LOG(INFO) << "DT2W debug: write contentsDec=" << contents
                      << " contentsHex=" << contentsHex
                      << " gestureBit=" << OplusTouchConstants::DOUBLE_TAP_GESTURE;
            int enableResult = 0;
            int indepResult = 0;
            auto enableStatus = oplusTouch->touchWriteNodeFile(
                    OplusTouchConstants::DEFAULT_TP_IC_ID,
                    OplusTouchConstants::DOUBLE_TAP_ENABLE_NODE, "1", &enableResult);
            auto indepStatus = oplusTouch->touchWriteNodeFile(
                    OplusTouchConstants::DEFAULT_TP_IC_ID,
                    OplusTouchConstants::DOUBLE_TAP_INDEP_NODE, contentsDec, &indepResult);
            LOG(INFO) << "DT2W debug: sync write enableStatus=" << enableStatus.getDescription()
                      << " enableResult=" << enableResult
                      << " indepStatus=" << indepStatus.getDescription()
                      << " indepResult=" << indepResult;
            if (std::string readback;
                oplusTouch->touchReadNodeFile(OplusTouchConstants::DEFAULT_TP_IC_ID,
                                              OplusTouchConstants::DOUBLE_TAP_INDEP_NODE,
                                              &readback)
                        .isOk()) {
                LOG(INFO) << "DT2W debug: readback OPlus node "
                          << OplusTouchConstants::DOUBLE_TAP_INDEP_NODE << " raw=" << readback;
            }
            return true;
        }
        default:
            return false;
    }
}

}  // namespace
