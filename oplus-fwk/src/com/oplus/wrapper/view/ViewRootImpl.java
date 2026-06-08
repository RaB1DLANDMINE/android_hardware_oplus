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

    public ViewRootImpl(android.view.ViewRootImpl viewRoot) {
    }

    public void addSurfaceChangedCallback(SurfaceChangedCallback changedCallback) {
    }

    public void removeSurfaceChangedCallback(SurfaceChangedCallback changedCallback) {
    }

    public Drawable createBackgroundBlurDrawable() {
        return null;
    }

    public void mergeWithNextTransaction(SurfaceControl.Transaction t, long frameNumber) {
    }

    public android.view.SurfaceControl getSurfaceControl() {
        return null;
    }

    public android.view.View getView() {
        return null;
    }

    public void registerRtFrameCallback(HardwareRenderer.FrameDrawingCallback callback) {
    }

    public void setCustomVriFlags(Bundle bundle) {
    }

    public IBinder getInputToken() {
        return null;
    }
}
