package com.oplus.graphics;

import android.os.SystemProperties;
import android.util.Log;
import com.oplus.view.OplusSmoothRoundedManager;
import java.util.Arrays;
import java.util.Objects;

public class OplusBlurParam {
    private static final int BLUR_PARAMS_NUM_SUM = 18;
    public static final int BLUR_BLEND_MODE_COLORDODGE = 3;
    public static final int BLUR_BLEND_MODE_COLORMIX = 1;
    public static final int BLUR_BLEND_MODE_DEFAULT = 0;
    public static final int BLUR_BLEND_MODE_GLOW_COLORDODGE = 5;
    public static final int BLUR_BLEND_MODE_GLOW_OVERLAY = 4;
    public static final int BLUR_BLEND_MODE_OVERLAY = 2;
    public static final int BLUR_TILE_MODE_CLAMP = 3;
    public static final int BLUR_TILE_MODE_DECAL = 4;
    public static final int BLUR_TILE_MODE_DEFAULT = 0;
    public static final int BLUR_TILE_MODE_MIRROR = 2;
    public static final int BLUR_TILE_MODE_REPEAT = 1;
    public static final int BLUR_TYPE_DEFAULT = 0;
    public static final int BLUR_TYPE_FAST_KAWASE = 2;
    public static final int BLUR_TYPE_GAUSSIAN = 4;
    public static final int BLUR_TYPE_ORIGINAL = 1;
    public static final int BLUR_TYPE_QUALITY_KAWASE = 3;
    public static final float DEFAULT_SMOOTH_CORNER_WEIGHT = 2.0f;
    private static final int CORNER_TYPE = SystemProperties.getInt("persist.ux.debug.corner_type", 0);
    private static final String TAG = "OplusBlurParam";

    private int mBlurType = BLUR_TYPE_DEFAULT;
    private int mTileMode = BLUR_TILE_MODE_DEFAULT;
    private float mZoomFactor = 1.0f;
    private int mBlendMode = BLUR_BLEND_MODE_DEFAULT;
    private final float[] mBlendColor = {0.0f, 0.0f, 0.0f, 0.0f};
    private final float[] mMixColor = {0.0f, 0.0f, 0.0f, 0.0f};
    private final float[] mBlurColor = {0.0f, 0.0f, 0.0f, 0.0f};
    private float mSmoothCornerWeight = OplusSmoothRoundedManager.getNonWight();
    private int mSmoothCornerType = OplusSmoothRoundedManager.getDefaultSmoothType();

    public OplusBlurParam() {
    }

    public void setBlurType(int blurType) {
        mBlurType = blurType;
    }

    public void setBlurRadius(int blurRadius) {
    }

    public void setMaterialParams(int material, float[] colorParams, float[] weightParams) {
        mBlendMode = material;
        copyColor(colorParams, mBlendColor);
        copyColor(weightParams, mMixColor);
    }

    public void setMirrorParams(int mode, float factor) {
        mTileMode = mode;
        mZoomFactor = factor;
    }

    public void setArcylicParams(float[] arcylicColor) {
        copyColor(arcylicColor, mBlurColor);
    }

    public void setSmoothCornerType(int cornerType) {
        mSmoothCornerType = cornerType;
    }

    public void setSmoothCornerWeight(float weight) {
        if (weight <= 0.0f) {
            Log.e(TAG, "IllegalArgument for setSmoothCornerWeight " + weight);
            return;
        }
        mSmoothCornerWeight = CORNER_TYPE == 0 ? weight
                : OplusSmoothRoundedManager.getDefaultG2Weight();
    }

    public float getSmoothCornerWeight() {
        return CORNER_TYPE == 0 ? mSmoothCornerWeight
                : OplusSmoothRoundedManager.getDefaultG2Weight();
    }

    public int getSmoothCornerType() {
        return CORNER_TYPE == 0 ? mSmoothCornerType : OplusSmoothRoundedManager.getG2CornerType();
    }

    public int getParamsSize() {
        return BLUR_PARAMS_NUM_SUM;
    }

    public float[] toFloatArray() {
        return new float[] {
                mBlurType,
                mTileMode,
                mZoomFactor,
                mBlendMode,
                mBlendColor[0],
                mBlendColor[1],
                mBlendColor[2],
                mBlendColor[3],
                mMixColor[0],
                mMixColor[1],
                mMixColor[2],
                mMixColor[3],
                mBlurColor[0],
                mBlurColor[1],
                mBlurColor[2],
                mBlurColor[3],
                mSmoothCornerWeight,
                mSmoothCornerType,
        };
    }

    public void fromFloatArray(float[] floatArray) {
        if (floatArray == null || floatArray.length != BLUR_PARAMS_NUM_SUM) {
            Log.w(TAG, "float array size is not equal params num in fromFloatArray");
            return;
        }
        mBlurType = Math.round(floatArray[0]);
        mTileMode = Math.round(floatArray[1]);
        mZoomFactor = floatArray[2];
        mBlendMode = Math.round(floatArray[3]);
        System.arraycopy(floatArray, 4, mBlendColor, 0, 4);
        System.arraycopy(floatArray, 8, mMixColor, 0, 4);
        System.arraycopy(floatArray, 12, mBlurColor, 0, 4);
        mSmoothCornerWeight = floatArray[16];
        mSmoothCornerType = Math.round(floatArray[17]);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OplusBlurParam)) {
            return false;
        }
        return Arrays.equals(toFloatArray(), ((OplusBlurParam) obj).toFloatArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mBlurType, mTileMode, mZoomFactor, mBlendMode,
                Arrays.hashCode(mBlendColor), Arrays.hashCode(mMixColor),
                Arrays.hashCode(mBlurColor), mSmoothCornerWeight, mSmoothCornerType);
    }

    private static void copyColor(float[] source, float[] target) {
        if (source == null) {
            return;
        }
        System.arraycopy(source, 0, target, 0, Math.min(source.length, target.length));
    }
}
