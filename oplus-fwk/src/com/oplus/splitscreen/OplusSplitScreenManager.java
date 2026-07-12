package com.oplus.splitscreen;

import com.oplus.app.IOplusSplitScreenObserver;

public class OplusSplitScreenManager {
    public static final int DIVIDER_INSETS_FOR_FOLDER = 21;
    public static final String EVENT_DISMISS_SPLIT_SCREEN = "dismissSplitScreen";
    public static final String EVENT_SPLIT_SCREEN_ANIMATION_STATE_CHANGED = "splitScreenAnimationStateChanged";
    public static final String EVENT_SPLIT_SCREEN_EXITING = "splitScreenExiting";
    public static final String EVENT_SPLIT_SCREEN_MINIMIZED_CHANGED = "splitScreenMinimizedChange";
    public static final String EVENT_SPLIT_SCREEN_MODE_CHANGED = "splitScreenModeChange";
    public static final int FIRST_OPLUS_EXIT_REASON_CODE = 200;
    public static final String IS_FLEXIBLE_ANIMATION_STATE = "isFlexibleAnimationState";
    public static final String KEY_DISMISS_SPLIT_SCREEN_TYPE = "dismissSplitScreenType";
    public static final String KEY_IS_EXITING = "isSplitExiting";
    public static final String KEY_IS_EXITING_MINIMIZED = "isExitingMinimized";
    public static final String KEY_IS_IN_SPLIT_SCREEN_MODE = "isInSplitScreenMode";
    public static final String KEY_IS_MINIMIZED = "isMinimized";
    public static final String KEY_POCKET_SPLIT_SCREEN_TYPE = "pocketSplitScreenType";
    public static final int LAUNCH_AREA_BOTTOM = 4;
    public static final int LAUNCH_AREA_INVALID = -1;
    public static final int LAUNCH_AREA_LEFT = 1;
    public static final int LAUNCH_AREA_RIGHT = 3;
    public static final int LAUNCH_AREA_TOP = 2;
    public static final int OPLUS_EXIT_REASON_APP_REQUEST = 200;
    public static final int OPLUS_EXIT_REASON_CONTROL_BAR_MENU_MAXIMIZE = 201;
    public static final int SPLIT_LEFT_OR_TOP_POSITION = 0;
    public static final int SPLIT_RIGHT_OR_BOTTOM_POSITION = 1;

    private static volatile OplusSplitScreenManager sInstance;

    private OplusSplitScreenManager() {
    }

    public static OplusSplitScreenManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusSplitScreenManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusSplitScreenManager();
                }
            }
        }
        return sInstance;
    }

    public boolean unregisterSplitScreenObserver(IOplusSplitScreenObserver observer) {
        return false;
    }
}
