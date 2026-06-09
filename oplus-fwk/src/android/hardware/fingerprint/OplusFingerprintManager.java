package android.hardware.fingerprint;

import android.content.Context;
import android.os.UserHandle;
import android.util.Log;

public class OplusFingerprintManager {
    private static final String TAG = "Biometrics/Fingerprint21/OplusFingerprintManager";

    private final FingerprintManager mFingerprintManager;

    public OplusFingerprintManager(Context context) {
        mFingerprintManager = context != null ? context.getSystemService(FingerprintManager.class) : null;
    }

    public boolean hasEnrolledTemplates(int userId) {
        if (mFingerprintManager == null) {
            Log.e(TAG, "hasEnrolledTemplates failed because FingerprintManager is unavailable");
            return false;
        }

        try {
            return mFingerprintManager.hasEnrolledFingerprints(userId);
        } catch (RuntimeException e) {
            Log.e(TAG, "hasEnrolledTemplates failed", e);
            return false;
        }
    }

    public boolean hasEnrolledTemplates() {
        return hasEnrolledTemplates(UserHandle.myUserId());
    }
}
