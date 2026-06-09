package com.oplus.graphics;

import android.graphics.Path;
import android.graphics.RectF;

public class OplusPathAdapter {
    public static final int NEW_PATH_SMOOTH = 1;
    public static final int OLD_PATH_SMOOTH = 0;

    private final Path mPath;

    public OplusPathAdapter(Path path, int styleType) {
        if (styleType != OLD_PATH_SMOOTH && styleType != NEW_PATH_SMOOTH) {
            throw new IllegalArgumentException("Invalid flag: " + styleType);
        }
        mPath = path;
    }

    public void addSmoothRoundRect(float left, float top, float right, float bottom,
            float rx, float ry, float weight, Path.Direction dir) {
        addSmoothRoundRect(left, top, right, bottom, rx, ry, dir);
    }

    public void addSmoothRoundRect(float left, float top, float right, float bottom,
            float rx, float ry, Path.Direction dir) {
        if (mPath != null) {
            mPath.addRoundRect(left, top, right, bottom, rx, ry, dir);
        }
    }

    public void addSmoothRoundRect(RectF rect, float rx, float ry, Path.Direction dir) {
        if (mPath != null && rect != null) {
            mPath.addRoundRect(rect, rx, ry, dir);
        }
    }

    public void addSmoothRoundRect(RectF rect, float rx, float ry, float weight,
            Path.Direction dir) {
        addSmoothRoundRect(rect, rx, ry, dir);
    }

    public void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir,
            float weight) {
        addSmoothRoundRect(rect, radii, dir);
    }

    public void addSmoothRoundRect(RectF rect, float[] radii, Path.Direction dir) {
        if (mPath != null && rect != null && radii != null) {
            mPath.addRoundRect(rect, radii, dir);
        }
    }
}
