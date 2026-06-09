package com.oplus.util;

import android.content.Context;

public class OplusPlatformLevelUtils {
    private static final int PLATFORM_LEVEL_MIDDLE = 2;
    private static final OplusPlatformLevelUtils INSTANCE = new OplusPlatformLevelUtils();

    public static OplusPlatformLevelUtils getInstance(Context context) {
        return INSTANCE;
    }

    public int getPlatformLevel(int type) {
        return PLATFORM_LEVEL_MIDDLE;
    }

    public int getPlatformAnimationLevel() {
        return PLATFORM_LEVEL_MIDDLE;
    }

    public int getPlatformGaussianLevel() {
        return PLATFORM_LEVEL_MIDDLE;
    }
}
