package com.oplus.flexiblewindow;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.res.Configuration;
import android.os.Bundle;

public class FlexibleWindowManager {
    private static final FlexibleWindowManager INSTANCE = new FlexibleWindowManager();

    public static FlexibleWindowManager getInstance() {
        return INSTANCE;
    }

    public static boolean isFlexibleActivitySuitable(Configuration configuration) {
        return false;
    }

    public int getFlexibleWindowState(Activity activity) {
        return 0;
    }

    public Bundle setExtraBundle(ActivityOptions options, Bundle bundle) {
        return bundle;
    }
}
