package com.oplus.osense;

import android.content.Context;
import android.os.Bundle;
import com.oplus.osense.eventinfo.EventConfig;
import com.oplus.osense.eventinfo.OsenseEventCallback;
import com.oplus.osense.task.BgRunningCallback;

public class OsenseResEventClient {
    private static final OsenseResEventClient INSTANCE = new OsenseResEventClient();

    public static OsenseResEventClient getInstance() {
        return INSTANCE;
    }

    public int registerEventCallback(OsenseEventCallback callback, EventConfig eventConfig) {
        return 0;
    }

    public int unregisterEventCallback(OsenseEventCallback callback, EventConfig eventConfig) {
        return 0;
    }

    public int unregisterEventCallback(OsenseEventCallback callback) {
        return 0;
    }

    public void requestSceneAction(Bundle bundle) {
    }

    public void startBackgroundRunning(Context context, int type, BgRunningCallback callback) {
    }

    public boolean stopBackgroundRunning(Context context, int type) {
        return false;
    }
}
