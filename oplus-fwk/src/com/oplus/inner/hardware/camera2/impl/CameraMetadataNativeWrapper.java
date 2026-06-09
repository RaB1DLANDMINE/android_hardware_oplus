package com.oplus.inner.hardware.camera2.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.util.Log;
import java.lang.reflect.Field;

public class CameraMetadataNativeWrapper {
    private static final String TAG = "CameraMetadataNativeWrapper";

    public CameraMetadataNativeWrapper() {
    }

    public static long getMetadataPtr(Object obj) {
        if (obj == null) {
            return 0L;
        }

        try {
            final Object nativeMeta;
            if (obj instanceof CameraCharacteristics) {
                Field properties = CameraCharacteristics.class.getDeclaredField("mProperties");
                properties.setAccessible(true);
                nativeMeta = properties.get(obj);
            } else if (obj instanceof CaptureRequest) {
                Field settings = CaptureRequest.class.getDeclaredField("mLogicalCameraSettings");
                settings.setAccessible(true);
                nativeMeta = settings.get(obj);
            } else if (obj instanceof CaptureResult) {
                Field results = CaptureResult.class.getDeclaredField("mResults");
                results.setAccessible(true);
                nativeMeta = results.get(obj);
            } else {
                nativeMeta = obj;
            }

            if (nativeMeta == null) {
                Log.e(TAG, "Unwrapped metadata is null");
                return 0L;
            }

            Field ptrField = nativeMeta.getClass().getDeclaredField("mMetadataPtr");
            ptrField.setAccessible(true);
            return ptrField.getLong(nativeMeta);
        } catch (Exception e) {
            Log.e(TAG, "Failed to get metadata ptr from " + obj.getClass().getName(), e);
            return 0L;
        }
    }
}
