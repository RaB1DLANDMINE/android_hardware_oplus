package com.oplus.wrapper.hardware.camera2;

import android.util.Log;
import java.lang.reflect.Field;

public class CameraCharacteristics {
    private static final String TAG = "CameraCharacteristicsWrapper";
    private final android.hardware.camera2.CameraCharacteristics mCharacteristics;

    public CameraCharacteristics(android.hardware.camera2.CameraCharacteristics characteristics) {
        mCharacteristics = characteristics;
    }

    public com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative getNativeMetadata() {
        if (mCharacteristics == null) {
            return null;
        }

        try {
            Field properties = android.hardware.camera2.CameraCharacteristics.class.getDeclaredField("mProperties");
            properties.setAccessible(true);
            return new com.oplus.wrapper.hardware.camera2.impl.CameraMetadataNative(properties.get(mCharacteristics));
        } catch (Exception e) {
            Log.e(TAG, "Failed to unwrap CameraCharacteristics metadata", e);
            return null;
        }
    }
}
