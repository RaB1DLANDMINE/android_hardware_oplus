package com.oplus.wrapper.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.view.SurfaceControl;
import com.oplus.wrapper.graphics.HardwareRenderer;

public class ViewRootImpl {
    public interface SurfaceChangedCallback {
        void surfaceCreated(SurfaceControl.Transaction transaction);

        void surfaceDestroyed();

        void surfaceReplaced(SurfaceControl.Transaction transaction);
    }

    private final android.view.ViewRootImpl mImpl;

    public ViewRootImpl(android.view.ViewRootImpl viewRoot) {
        mImpl = viewRoot;
    }

    public void addSurfaceChangedCallback(SurfaceChangedCallback changedCallback) {
    }

    public void removeSurfaceChangedCallback(SurfaceChangedCallback changedCallback) {
    }

    public Drawable createBackgroundBlurDrawable() {
        return mImpl == null ? null : mImpl.createBackgroundBlurDrawable();
    }

    public void mergeWithNextTransaction(SurfaceControl.Transaction t, long frameNumber) {
        if (mImpl != null) {
            mImpl.mergeWithNextTransaction(t, frameNumber);
        }
    }

    public android.view.SurfaceControl getSurfaceControl() {
        return mImpl == null ? null : mImpl.getSurfaceControl();
    }

    public android.view.View getView() {
        return mImpl == null ? null : mImpl.getView();
    }

    public void registerRtFrameCallback(HardwareRenderer.FrameDrawingCallback callback) {
    }

    public void setCustomVriFlags(Bundle bundle) {
    }

    public IBinder getInputToken() {
        return mImpl == null ? null : mImpl.getInputToken();
    }
}
