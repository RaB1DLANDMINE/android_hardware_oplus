package com.oplus.view;

import android.graphics.RenderEffect;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;

public final class OplusViewBackgroundRenderEffect {
    private static final String TAG = "OplusViewBackgroundRenderEffect";

    public static final int TYPE_VFX_BOOTREG_RIPPLE = 1024;
    public static final int TYPE_VFX_CHARGING_RIPPLE = 512;
    public static final int TYPE_VFX_CLOSE = 0;
    public static final int TYPE_VFX_DISPLAY_RIPPLE = 64;
    public static final int TYPE_VFX_FP_RIPPLE = 256;
    public static final int TYPE_VFX_HSL_ANIMATION = 2;
    public static final int TYPE_VFX_INSTALL_RIPPLE = 128;
    public static final int TYPE_VFX_OVAL_FP = 4;
    public static final int TYPE_VFX_OVAL_USB = 8;
    public static final int TYPE_VFX_OVAL_WIRELESS = 16;
    public static final int TYPE_VFX_RUNNING = 1;
    public static final int TYPE_VFX_SINGLE_RIPPLE = 32;

    private OplusViewBackgroundRenderEffect() {
    }

    public static void setBackgroundRenderEffect(RenderEffect renderEffect, View view) {
        if (view == null) {
            return;
        }
        view.setBackdropRenderEffect(renderEffect);
    }

    public static boolean startVfxAnimationBySurfaceController(SurfaceControl.Transaction transaction,
            int type, float effectDegree, float[] center, float startRadius, float endRadius,
            SurfaceControl surfaceControl) {
        if (!isParametersCompliant(transaction, effectDegree, center, startRadius, endRadius)) {
            return false;
        }
        if (surfaceControl == null) {
            Log.e(TAG, "startVfxAnimationBySurfaceController surfaceControl cannot null");
            return false;
        }
        return false;
    }

    public static boolean startVfxAnimationByView(SurfaceControl.Transaction transaction, int type,
            float effectDegree, float[] center, float startRadius, float endRadius, View target) {
        if (!isParametersCompliant(transaction, effectDegree, center, startRadius, endRadius)) {
            return false;
        }
        if (target == null) {
            Log.e(TAG, "startVfxAnimationByView target cannot null");
            return false;
        }
        if (!target.isAttachedToWindow()) {
            Log.e(TAG, "startVfxAnimationByView view not attached to window");
            return false;
        }
        return false;
    }

    private static boolean isParametersCompliant(SurfaceControl.Transaction transaction,
            float effectDegree, float[] center, float startRadius, float endRadius) {
        if (transaction == null) {
            Log.e(TAG, "isParametersCompliant transaction cannot null");
            return false;
        }
        if (center == null) {
            Log.e(TAG, "isParametersCompliant center cannot null");
            return false;
        }
        if (center.length != 2) {
            Log.e(TAG, "isParametersCompliant center point length cannot 2");
            return false;
        }
        if (effectDegree < 0.0f || startRadius < 0.0f || endRadius < 0.0f) {
            Log.e(TAG, "isParametersCompliant radius/progress cannot be negative");
            return false;
        }
        return true;
    }
}
