package android.hardware.face;

import android.content.Context;
import android.os.UserHandle;
import android.util.Log;

public class OplusFaceManager {
    public static final int PALMS_SENSOR_ID = 4;
    public static final String TAG = "OplusFaceManager";
    public static final int TYPE_FACE = 0;
    public static final int TYPE_PALM = 1;

    private final Context mContext;
    private final FaceManager mFaceManager;

    public OplusFaceManager(Context context) {
        mContext = context;
        mFaceManager = context != null ? context.getSystemService(FaceManager.class) : null;
    }

    public boolean hasEnrolledTemplates() {
        if (mFaceManager == null) {
            Log.e(TAG, "hasEnrolledTemplates failed because FaceManager is unavailable");
            return false;
        }

        try {
            return mFaceManager.hasEnrolledTemplates(UserHandle.myUserId());
        } catch (RuntimeException e) {
            Log.e(TAG, "hasEnrolledTemplates failed", e);
            return false;
        }
    }

    public boolean hasEnrolledPalms() {
        return false;
    }
}
