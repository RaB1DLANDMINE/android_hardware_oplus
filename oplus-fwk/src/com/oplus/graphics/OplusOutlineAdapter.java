package com.oplus.graphics;

import android.graphics.Outline;
import android.graphics.Rect;

public class OplusOutlineAdapter {
    public static final int NEW_OUTLINE_SMOOTH = 1;
    public static final int OLD_OUTLINE_SMOOTH = 0;

    private final Outline mOutline;

    public OplusOutlineAdapter(Outline outline, int styleType) {
        if (styleType != OLD_OUTLINE_SMOOTH && styleType != NEW_OUTLINE_SMOOTH) {
            throw new IllegalArgumentException("Invalid flag: " + styleType);
        }
        mOutline = outline;
    }

    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius,
            float weight) {
        setSmoothRoundRect(left, top, right, bottom, radius);
    }

    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius) {
        if (mOutline != null) {
            mOutline.setRoundRect(left, top, right, bottom, radius);
        }
    }

    public void setSmoothRoundRect(Rect rect, float radius, float weight) {
        setSmoothRoundRect(rect, radius);
    }

    public void setSmoothRoundRect(Rect rect, float radius) {
        if (mOutline != null && rect != null) {
            mOutline.setRoundRect(rect, radius);
        }
    }
}
