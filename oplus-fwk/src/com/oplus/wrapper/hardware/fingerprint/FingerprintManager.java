package com.oplus.wrapper.hardware.fingerprint;

import android.util.Log;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class FingerprintManager {
    private static final String TAG = "FingerprintManagerWrapper";
    private final android.hardware.fingerprint.FingerprintManager mFingerprintManager;

    public FingerprintManager(android.hardware.fingerprint.FingerprintManager fingerprintManager) {
        mFingerprintManager = fingerprintManager;
    }

    public List<Fingerprint> getEnrolledFingerprints(int userId) {
        if (mFingerprintManager == null) {
            return Collections.emptyList();
        }

        try {
            Method method = android.hardware.fingerprint.FingerprintManager.class
                    .getDeclaredMethod("getEnrolledFingerprints", int.class);
            method.setAccessible(true);
            Object result = method.invoke(mFingerprintManager, userId);
            if (!(result instanceof List)) {
                return Collections.emptyList();
            }

            List<?> fingerprints = (List<?>) result;
            List<Fingerprint> wrapped = new ArrayList<>(fingerprints.size());
            for (Object fingerprint : fingerprints) {
                if (fingerprint instanceof Fingerprint) {
                    wrapped.add((Fingerprint) fingerprint);
                } else {
                    wrapped.add(new Fingerprint(fingerprint));
                }
            }
            return wrapped;
        } catch (ReflectiveOperationException | RuntimeException e) {
            Log.e(TAG, "Failed to get enrolled fingerprints", e);
            return Collections.emptyList();
        }
    }
}
