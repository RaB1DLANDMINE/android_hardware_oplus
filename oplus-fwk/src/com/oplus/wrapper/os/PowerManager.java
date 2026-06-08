package com.oplus.wrapper.os;

public class PowerManager {
    public static final int USER_ACTIVITY_EVENT_OTHER = 0;
    public static final int WAKE_REASON_APPLICATION = 2;

    public PowerManager(android.os.PowerManager powerManager) {
    }

    public int getMaximumScreenBrightnessSetting() {
        return 0;
    }

    public int getMinimumScreenBrightnessSetting() {
        return 0;
    }

    public void wakeUp(long time, String details) {
    }

    public void wakeUp(long time, int reason, String details) {
    }

    public int getDefaultScreenBrightnessSetting() {
        return 0;
    }

    public PowerSaveState getPowerSaveState(int serviceType) {
        return null;
    }

    public int getLastSleepReason() {
        return 0;
    }

    public void shutdown(boolean confirm, String reason, boolean wait) {
    }

    public void reboot(String reason) {
    }

    public void goToSleep(long time) {
    }

    public void userActivity(long when, int event, int flags) {
    }

    public boolean setPowerSaveModeEnabled(boolean mode) {
        return false;
    }

    public static class PowerSaveState {
        public PowerSaveState(Object powerSaveState) {
        }
    }
}
