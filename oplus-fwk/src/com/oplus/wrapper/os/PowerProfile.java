package com.oplus.wrapper.os;

import android.content.Context;

public class PowerProfile {
    private final com.android.internal.os.PowerProfile mPowerProfile;

    public PowerProfile(Context context) {
        mPowerProfile = new com.android.internal.os.PowerProfile(context);
    }

    public double getBatteryCapacity() {
        return mPowerProfile.getBatteryCapacity();
    }

    public double getAveragePower(String type) {
        return mPowerProfile.getAveragePower(type);
    }
}
