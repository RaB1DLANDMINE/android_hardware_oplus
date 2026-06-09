package com.oplus.graphics;

import android.graphics.Path;
import android.graphics.RectF;

public class OplusPath {
    private final Path mPath;

    public OplusPath(Path path) {
        mPath = path;
    }

    public void addSmoothRoundRect(RectF rect, float rx, float ry, Path.Direction dir) {
        if (mPath != null && rect != null) {
            mPath.addRoundRect(rect, rx, ry, dir);
        }
    }

    public void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir, float weight) {
        if (mPath != null && rect != null && radii != null) {
            mPath.addRoundRect(rect, radii, dir);
        }
    }

    public void addSmoothRoundRect(RectF rect, float rx, float ry, float weight,
            Path.Direction dir) {
        addSmoothRoundRect(rect, rx, ry, dir);
    }

    public void addSmoothRoundRect(float left, float top, float right, float bottom,
            float rx, float ry, float weight, Path.Direction dir) {
        if (mPath != null) {
            mPath.addRoundRect(left, top, right, bottom, rx, ry, dir);
        }
    }
}
