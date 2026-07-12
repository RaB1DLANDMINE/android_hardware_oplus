package com.oplus.wrapper.hardware.camera2;

import android.util.Log;
import java.lang.reflect.Field;

public class CameraMetadata<T> {
    private static final String TAG = "CameraMetadataWrapper";
    private final android.hardware.camera2.CameraMetadata<T> mCameraMetadata;

    public CameraMetadata(android.hardware.camera2.CameraMetadata<T> cameraMetadata) {
        mCameraMetadata = cameraMetadata;
    }

    public long getNativeMetadataPtr() {
        if (mCameraMetadata == null) {
            return 0L;
        }

        try {
            Field ptrField = android.hardware.camera2.CameraMetadata.class.getDeclaredField("mMetadataPtr");
            ptrField.setAccessible(true);
            return ptrField.getLong(mCameraMetadata);
        } catch (Exception e) {
            Log.e(TAG, "Failed to get native metadata ptr", e);
            return 0L;
        }
    }
}
