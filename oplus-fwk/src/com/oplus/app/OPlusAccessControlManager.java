package com.oplus.app;

import android.os.UserHandle;
import java.util.HashMap;
import java.util.Map;

public class OPlusAccessControlManager {
    private static volatile OPlusAccessControlManager sInstance = null;
    public static final int USER_CURRENT = UserHandle.myUserId();

    private OPlusAccessControlManager() {
    }

    public static OPlusAccessControlManager getInstance() {
        if (sInstance == null) {
            synchronized (OPlusAccessControlManager.class) {
                if (sInstance == null) {
                    sInstance = new OPlusAccessControlManager();
                }
            }
        }
        return sInstance;
    }

    public void setAccessControlAppsInfo(String type, Map<String, Integer> accessControlInfo, int userId) {
    }

    public Map<String, Integer> getAccessControlAppsInfo(String type, int userId) {
        return new HashMap<>();
    }

    public void setAccessControlEnabled(String type, boolean enable, int userId) {
    }

    public boolean getAccessControlEnabled(String type, int userId) {
        return false;
    }

    public void addEncryptPass(String packageName, int windowMode, int userId) {
    }

    public boolean isEncryptPass(String packageName, int userId) {
        return false;
    }

    public boolean isEncryptedPackage(String packageName, int userId) {
        return false;
    }

    public void setPrivacyAppsInfoForUser(Map<String, Integer> privacyInfo, boolean enabled, int userId) {
    }

    public boolean getApplicationAccessControlEnabledAsUser(String packageName, int userId) {
        return false;
    }

    public void addAccessControlPassForUser(String packageName, int windowMode, int userId) {
    }

    public Map<String, Integer> getPrivacyAppInfo(int userId) {
        return new HashMap<>();
    }

    public boolean isAccessControlPassForUser(String packageName, int userId) {
        return false;
    }
}
