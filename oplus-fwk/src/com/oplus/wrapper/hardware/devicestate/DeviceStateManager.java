package com.oplus.wrapper.hardware.devicestate;

import android.content.Context;
import java.util.concurrent.Executor;

public class DeviceStateManager {
    public interface DeviceStateCallback {
        void onDeviceStateChanged(DeviceState deviceState);
    }

    public DeviceStateManager(Context context) {
    }

    public void registerCallback(Executor executor, DeviceStateCallback callback) {
    }

    public void unregisterCallback(DeviceStateCallback callback) {
    }
}
