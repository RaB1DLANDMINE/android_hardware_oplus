package com.oplus.graphics;

import android.graphics.RenderEffect;
import android.util.MathUtils;

public final class OplusRenderEffect {
    private OplusRenderEffect() {
    }

    public static RenderEffect createGradientBlurEffect(float startRadius, float endRadius,
            boolean isVertical, float speed) {
        return createGradientBlurEffect(startRadius, endRadius, isVertical, speed, 0, 0, 0);
    }

    public static RenderEffect createGradientBlurEffect(float startRadius, float endRadius,
            boolean isVertical, float speed, int blendMode, int blendC, int mixC) {
        return createGradientBlurEffect(startRadius, endRadius, isVertical, speed, blendMode,
                blendC, mixC, null);
    }

    public static RenderEffect createGradientBlurEffect(float startRadius, float endRadius,
            boolean isVertical, float speed, int blendMode, int blendC, int mixC,
            RenderEffect inputEffect) {
        return RenderEffect.createGradientBlurEffect(MathUtils.max(0.0f, startRadius),
                MathUtils.max(0.0f, endRadius), isVertical, MathUtils.max(0.0f, speed),
                blendMode, blendC, mixC, inputEffect);
    }
}
