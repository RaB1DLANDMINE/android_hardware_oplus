package com.oplus.inner.view;

import android.util.Log;
import android.view.View;

public class ViewWrapper {
    private static final String TAG = "ViewWrapper";

    public static void setScrollXForColor(View view, int x) {
        if (view == null) {
            return;
        }

        try {
            view.setScrollX(x);
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void setScrollYForColor(View view, int y) {
        if (view == null) {
            return;
        }

        try {
            view.setScrollY(y);
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
        }
    }

    public static boolean requestAccessibilityFocus(View view) {
        if (view == null) {
            return false;
        }

        try {
            return view.requestAccessibilityFocus();
        } catch (Throwable e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }
}
