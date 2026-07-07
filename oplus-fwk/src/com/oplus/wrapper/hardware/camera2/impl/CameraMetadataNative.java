package com.oplus.wrapper.hardware.camera2.impl;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

    public static int getTag(String key, long vendorId) {
        try {
            Class<?> metadataClass = Class.forName("android.hardware.camera2.impl.CameraMetadataNative");
            Method getTag = metadataClass.getDeclaredMethod("getTag", String.class, long.class);
            getTag.setAccessible(true);
            return (Integer) getTag.invoke(null, key, vendorId);
        } catch (Exception e) {
            Log.e(TAG, "Failed to get metadata tag for " + key, e);
            return 0;
        }
    }
}
