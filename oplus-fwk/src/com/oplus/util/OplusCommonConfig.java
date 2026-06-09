package com.oplus.util;

import android.app.OplusActivityManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

public class OplusCommonConfig {
    public static final int TO_AMS = 1;
    public static final int TO_PMS = 2;

    private static final String TAG = "OplusCommonConfig";
    private static volatile OplusCommonConfig sConfig;

    private OplusActivityManager mOplusActivityManager = new OplusActivityManager();

    private OplusCommonConfig() {
    }

    public static OplusCommonConfig getInstance() {
        if (sConfig == null) {
            synchronized (OplusCommonConfig.class) {
                if (sConfig == null) {
                    sConfig = new OplusCommonConfig();
                }
            }
        }
        return sConfig;
    }

    public boolean putConfigInfo(String configName, Bundle bundle, int flag) {
        return putConfigInfoAsUser(configName, bundle, flag, UserHandle.myUserId());
    }

    public boolean putConfigInfoAsUser(String configName, Bundle bundle, int flag, int userId) {
        ensureActivityManager();
        try {
            return mOplusActivityManager.putConfigInfo(configName, bundle, flag, userId);
        } catch (RemoteException e) {
            Log.e(TAG, "putConfigInfoAsUser " + configName + " failed!", e);
            return false;
        }
    }

    public Bundle getConfigInfo(String configName, int flag) {
        return getConfigInfoAsUser(configName, flag, UserHandle.myUserId());
    }

    public Bundle getConfigInfoAsUser(String configName, int flag, int userId) {
        ensureActivityManager();
        try {
            return mOplusActivityManager.getConfigInfo(configName, flag, userId);
        } catch (RemoteException e) {
            Log.e(TAG, "getConfigInfoAsUser " + configName + " failed!", e);
            return null;
        }
    }

    private void ensureActivityManager() {
        if (mOplusActivityManager == null) {
            mOplusActivityManager = new OplusActivityManager();
        }
    }
}
