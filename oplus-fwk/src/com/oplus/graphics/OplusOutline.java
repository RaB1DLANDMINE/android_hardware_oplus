package com.oplus.graphics;

import android.graphics.Outline;
import android.graphics.Rect;

public class OplusOutline {
    private final Outline mOutline;

    public OplusOutline(Outline outline) {
        mOutline = outline;
    }

    public void setSmoothRoundRect(int left, int top, int right, int bottom, float radius,
            float weight) {
        if (mOutline != null) {
            mOutline.setRoundRect(left, top, right, bottom, radius);
        }
    }

    public void setSmoothRoundRect(Rect rect, float radius, float weight) {
        if (mOutline != null && rect != null) {
            mOutline.setRoundRect(rect, radius);
        }
    }
}
