package com.oplus.view;

import android.os.Bundle;
import android.view.View;

public class OplusView {
    private final View mView;

    public OplusView(View view) {
        mView = view;
    }

    public boolean canReceivePointerEvents() {
        return mView != null;
    }

    public boolean setFrame(int left, int top, int right, int bottom) {
        return false;
    }

    public void damageInParent() {
        if (mView != null) {
            mView.invalidate();
        }
    }

    public void setOverrideLightSourceGeometry(
            float lightX, float lightY, float lightZ, float lightRadius, float blurRadius) {
    }

    public boolean updateDragShadowBadge(
            View.DragShadowBuilder shadowBuilder,
            float dx,
            float dy,
            String content,
            int status,
            Bundle bundle) {
        return false;
    }

    public boolean updateDragShadowBadge(
            View.DragShadowBuilder shadowBuilder,
            float dx,
            float dy,
            String content,
            int status,
            Runnable endAction,
            Bundle bundle) {
        return false;
    }
}
