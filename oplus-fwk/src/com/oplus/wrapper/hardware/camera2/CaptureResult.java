package com.oplus.wrapper.hardware.camera2;

import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CaptureResult {
    private static final String TAG = "CaptureResultWrapper";
    private final android.hardware.camera2.CaptureResult mResult;

    public CaptureResult() {
        mResult = null;
    }

    public CaptureResult(android.hardware.camera2.CaptureResult result) {
        mResult = result;
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

    public com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative getNativeCopy() {
        if (mResult == null) {
            return null;
        }

        try {
            Method getNativeCopy = android.hardware.camera2.CaptureResult.class.getDeclaredMethod("getNativeCopy");
            getNativeCopy.setAccessible(true);
            Object metadata = getNativeCopy.invoke(mResult);
            if (metadata == null) {
                return null;
            }
            return new com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative(metadata);
        } catch (Exception e) {
            Log.e(TAG, "Failed to copy CaptureResult metadata", e);
            return null;
        }
    }

    public static final class Key<T> {
        private final android.hardware.camera2.CaptureResult.Key<T> mKey;

        public Key(android.hardware.camera2.CaptureResult.Key<T> key) {
            mKey = key;
        }

        public Key(String name, Class<T> type, long vendorId) {
            mKey = new android.hardware.camera2.CaptureResult.Key<>(name, type, vendorId);
        }

        public Key(String name, Class<T> type) {
            mKey = new android.hardware.camera2.CaptureResult.Key<>(name, type);
        }

        public Key(String name, String fallbackName, Class<T> type) {
            mKey = newKey(name, fallbackName, type);
        }

        public android.hardware.camera2.CaptureResult.Key<T> getKey() {
            return mKey;
        }

        public long getVendorId() {
            return mKey.getVendorId();
        }

        private static <T> android.hardware.camera2.CaptureResult.Key<T> newKey(
                String name, String fallbackName, Class<T> type) {
            try {
                Constructor<android.hardware.camera2.CaptureResult.Key> constructor =
                        android.hardware.camera2.CaptureResult.Key.class.getDeclaredConstructor(
                                String.class, String.class, Class.class);
                constructor.setAccessible(true);
                return constructor.newInstance(name, fallbackName, type);
            } catch (Exception e) {
                Log.e(TAG, "Failed to create fallback CaptureResult key", e);
                return new android.hardware.camera2.CaptureResult.Key<>(name, type);
            }
        }
    }
}
