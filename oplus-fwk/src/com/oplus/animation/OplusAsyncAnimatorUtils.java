package com.oplus.animation;

import android.graphics.Rect;
import android.graphics.RenderNode;
import android.view.View;

public class OplusAsyncAnimatorUtils {
    public static boolean setAlpha(View view, float alpha) {
        if (view == null) {
            return false;
        }
        view.setAlpha(alpha);
        return true;
    }

    public static boolean setAlpha(RenderNode renderNode, float alpha) {
        if (renderNode == null) {
            return false;
        }
        renderNode.setAlpha(alpha);
        return true;
    }

    public static boolean setOutlineRoundRect(View view, Rect rect, float radius, float alpha) {
        if (view == null) {
            return false;
        }
        view.setClipBounds(rect);
        return true;
    }

    public static boolean setScaleX(View view, float scale) {
        if (view == null) {
            return false;
        }
        view.setScaleX(scale);
        return true;
    }

    public static boolean setScaleY(View view, float scale) {
        if (view == null) {
            return false;
        }
        view.setScaleY(scale);
        return true;
    }

    public static boolean setTranslationX(View view, float translation) {
        if (view == null) {
            return false;
        }
        view.setTranslationX(translation);
        return true;
    }

    public static boolean setTranslationY(View view, float translation) {
        if (view == null) {
            return false;
        }
        view.setTranslationY(translation);
        return true;
    }
}
