package com.oplus.app;

import android.content.Context;

public class OplusAppSwitchManager {
    public static int APP_SWITCH_VERSION = 1;

    private static final OplusAppSwitchManager INSTANCE = new OplusAppSwitchManager();

    public static OplusAppSwitchManager getInstance() {
        return INSTANCE;
    }

    public boolean registerAppSwitchObserver(Context context, OnAppSwitchObserver observer,
            OplusAppSwitchConfig config) {
        return true;
    }

    public boolean unregisterAppSwitchObserver(Context context, OnAppSwitchObserver observer) {
        return true;
    }

    public interface OnAppSwitchObserver {
        void onActivityEnter(OplusAppEnterInfo info);
        void onActivityExit(OplusAppExitInfo info);
        void onAppEnter(OplusAppEnterInfo info);
        void onAppExit(OplusAppExitInfo info);
    }
}
