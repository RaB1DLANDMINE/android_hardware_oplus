package com.oplus.wrapper.hardware.camera2.impl;

import android.util.Log;
import java.lang.reflect.Field;

public class CameraMetadataNative {
    private static final String TAG = "CameraMetadataNativeWrapper";
    private final Object mMetadata;

    public CameraMetadataNative() {
        mMetadata = null;
    }

    public CameraMetadataNative(Object metadata) {
        mMetadata = metadata;
    }

    public long getMetadataPtr() {
        if (mMetadata == null) {
            return 0L;
        }

        try {
            Field ptrField = mMetadata.getClass().getDeclaredField("mMetadataPtr");
            ptrField.setAccessible(true);
            return ptrField.getLong(mMetadata);
        } catch (Exception e) {
            Log.e(TAG, "Failed to get metadata ptr from " + mMetadata.getClass().getName(), e);
            return 0L;
        }
    }
}
