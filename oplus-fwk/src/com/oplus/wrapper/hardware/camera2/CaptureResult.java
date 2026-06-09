package com.oplus.wrapper.hardware.camera2;

import android.util.Log;
import java.lang.reflect.Field;

public class CaptureResult {
    private static final String TAG = "CaptureResultWrapper";
    private final android.hardware.camera2.CaptureResult mResult;

    public CaptureResult() {
        mResult = null;
    }

    public CaptureResult(android.hardware.camera2.CaptureResult r) {
        mResult = r;
    }

    public com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative getNativeMetadata() {
        if (mResult == null) {
            return null;
        }

        try {
            Field results = android.hardware.camera2.CaptureResult.class.getDeclaredField("mResults");
            results.setAccessible(true);
            return new com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative(results.get(mResult));
        } catch (Exception e) {
            Log.e(TAG, "Failed to unwrap CaptureResult metadata", e);
            return null;
        }
    }
}
