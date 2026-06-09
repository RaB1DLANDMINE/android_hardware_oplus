package com.oplus.wrapper.view;

import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

public class View {
    public static final int DRAG_FLAG_REQUEST_SURFACE_FOR_RETURN_ANIMATION = 2048;

    private final android.view.View mView;

    public View(android.view.View view) {
        mView = view;
    }

    public static boolean isVisibleToUser(android.view.View target) {
        return false;
    }

    public static boolean requestAccessibilityFocus(android.view.View target) {
        return false;
    }

    public static boolean isLayoutRtl(android.view.View target) {
        return false;
    }

    public static ViewRootImpl getViewRootImpl(android.view.View target) {
        if (target == null) {
            return null;
        }
        android.view.ViewRootImpl vri = target.getViewRootImpl();
        return vri == null ? null : new ViewRootImpl(vri);
    }

    public static void notifyViewAccessibilityStateChangedIfNeeded(android.view.View target, int changeType) {
    }

    public static void onMovedToDisplay(android.view.View target, int displayId, Configuration config) {
    }

    public static void clearAccessibilityFocus(android.view.View target) {
    }

    public static final boolean hasIdentityMatrix(android.view.View target) {
        return false;
    }

    public static final Matrix getInverseMatrix(android.view.View target) {
        return null;
    }

    public static int[] getLocationOnScreen(android.view.View target) {
        return null;
    }

    public static int getPrivateFlags(android.view.View target) {
        return 0;
    }

    public static void setPrivateFlags(android.view.View target, int privateFlags) {
    }

    public static boolean isForegroundInsidePadding(android.view.View target) {
        return false;
    }

    public static void getBoundsOnScreen(android.view.View target, Rect outRect) {
    }

    public static AccessibilityNodeInfo createAccessibilityNodeInfo(android.view.View target) {
        return null;
    }

    public static void setSkipFirstFrameDraw(android.view.View target, boolean skip) {
    }

    public boolean isVisibleToUser() {
        return false;
    }

    public boolean requestAccessibilityFocus() {
        return false;
    }

    public boolean isLayoutRtl() {
        return false;
    }

    public ViewRootImpl getViewRootImpl() {
        if (mView == null) {
            return null;
        }
        android.view.ViewRootImpl vri = mView.getViewRootImpl();
        return vri == null ? null : new ViewRootImpl(vri);
    }

    public void notifyViewAccessibilityStateChangedIfNeeded(int changeType) {
    }

    public void onMovedToDisplay(int displayId, Configuration config) {
    }

    public void clearAccessibilityFocus() {
    }

    public final boolean hasIdentityMatrix() {
        return false;
    }

    public final Matrix getInverseMatrix() {
        return null;
    }

    public int[] getLocationOnScreen() {
        return null;
    }

    public int getPrivateFlags() {
        return 0;
    }

    public void setPrivateFlags(int privateFlags) {
    }

    public boolean isForegroundInsidePadding() {
        return false;
    }

    public void getBoundsOnScreen(Rect outRect) {
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo() {
        return null;
    }

    public static class AccessibilityDelegate {
        public AccessibilityDelegate(android.view.View.AccessibilityDelegate accessibilityDelegate) {
        }

        public static AccessibilityNodeInfo createAccessibilityNodeInfo(android.view.View.AccessibilityDelegate target, android.view.View host) {
            return null;
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(android.view.View host) {
            return null;
        }
    }
}
