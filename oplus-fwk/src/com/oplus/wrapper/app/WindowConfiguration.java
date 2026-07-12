package com.oplus.wrapper.app;

import android.graphics.Rect;

public class WindowConfiguration {
    public static final int WINDOWING_MODE_FULLSCREEN = 1;
    @Deprecated
    public static final int WINDOWING_MODE_SPLIT_SCREEN_PRIMARY = 0;
    @Deprecated
    public static final int WINDOWING_MODE_SPLIT_SCREEN_SECONDARY = 0;
    public static final int WINDOWING_MODE_UNDEFINED = 0;
    public static final int WINDOWING_MODE_PINNED = 2;
    public static final int WINDOWING_MODE_FREEFORM = 5;
    public static final int WINDOWING_MODE_MULTI_WINDOW = 6;
    public static final int ROTATION_UNDEFINED = -1;
    public static final int ACTIVITY_TYPE_UNDEFINED = 0;
    public static final int ACTIVITY_TYPE_STANDARD = 1;
    public static final int ACTIVITY_TYPE_HOME = 2;
    public static final int ACTIVITY_TYPE_RECENTS = 3;

    public WindowConfiguration() {
    }

    public WindowConfiguration(android.app.WindowConfiguration windowConfiguration) {
    }

    public android.app.WindowConfiguration getmWindowConfiguration() {
        return null;
    }

    public Rect getMaxBounds() {
        return null;
    }

    public int getActivityType() {
        return 0;
    }

    public Rect getAppBounds() {
        return null;
    }

    public Rect getBounds() {
        return null;
    }

    public int getRotation() {
        return 0;
    }

    public int getWindowingMode() {
        return 0;
    }

    public void setAppBounds(Rect rect) {
    }

    public void setWindowingMode(int windowingMode) {
    }
}
