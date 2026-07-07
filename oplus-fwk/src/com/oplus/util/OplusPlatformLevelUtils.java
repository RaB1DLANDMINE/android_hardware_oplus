package com.oplus.util;

import android.content.Context;
import android.os.SystemProperties;

public class OplusPlatformLevelUtils {
    public static final int LEVEL_UNDEF = -1;
    public static final int LEVEL_LOW = 1;
    public static final int LEVEL_MIDDLE = 2;
    public static final int LEVEL_HIGH = 3;
    public static final int LEVEL_TYPE_FOR_RAM = 1;
    public static final int LEVEL_TYPE_FOR_CPU = 2;
    public static final int LEVEL_TYPE_FOR_GPU = 3;

    public static final boolean IS_LIGHT_OS = SystemProperties.getBoolean("ro.oplus.lightos", false);

    private static volatile OplusPlatformLevelUtils sInstance;

    private OplusPlatformLevelUtils(Context context) {
    }

    public static OplusPlatformLevelUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusPlatformLevelUtils.class) {
                if (sInstance == null) {
                    sInstance = new OplusPlatformLevelUtils(context);
                }
            }
        }
        return sInstance;
    }

    public int getPlatformLevel(int type) {
        return LEVEL_HIGH;
    }

    public int getPlatformAnimationLevel() {
        return SystemProperties.getInt("ro.oplus.animationlevel", 0);
    }

    public int getPlatformGaussianLevel() {
        return SystemProperties.getInt("ro.oplus.gaussianlevel", 0);
    }
}
