package android.os.customize;

import android.content.Context;

/** Stub for OEM MDM state manager. OplusCamera obtains the singleton. No-op. */
public class OplusCustomizeStateManager {
    private static final OplusCustomizeStateManager INSTANCE = new OplusCustomizeStateManager();

    public static OplusCustomizeStateManager getInstance(Context context) {
        return INSTANCE;
    }
}
