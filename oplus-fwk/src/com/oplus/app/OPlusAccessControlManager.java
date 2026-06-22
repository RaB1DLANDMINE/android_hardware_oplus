package com.oplus.app;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class OPlusAccessControlManager {
    private static final String TAG = "OPlusAccessControlManager";
    private static final String SERVICE_NAME = "oplus_accesscontrol";
    private static final String TYPE_ENCRYPT = "type_encrypt";

    private static volatile OPlusAccessControlManager sInstance = null;
    public static final int USER_CURRENT = UserHandle.myUserId();

    private volatile IOplusAccessControlManager mService;

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

    private IOplusAccessControlManager getService() {
        IOplusAccessControlManager service = mService;
        if (service != null) {
            return service;
        }
        IBinder binder = ServiceManager.getService(SERVICE_NAME);
        if (binder == null) {
            Log.w(TAG, "service not found: " + SERVICE_NAME);
            return null;
        }
        service = IOplusAccessControlManager.Stub.asInterface(binder);
        mService = service;
        return service;
    }

    private void clearService() {
        mService = null;
    }

    public void setAccessControlAppsInfo(String type, Map<String, Integer> accessControlInfo, int userId) {
        IOplusAccessControlManager service = getService();
        if (service == null) {
            return;
        }
        try {
            service.setAccessControlAppsInfo(type, accessControlInfo, userId);
        } catch (RemoteException e) {
            clearService();
            Log.w(TAG, "setAccessControlAppsInfo failed", e);
        }
    }

    public Map<String, Integer> getAccessControlAppsInfo(String type, int userId) {
        IOplusAccessControlManager service = getService();
        if (service == null) {
            return new HashMap<>();
        }
        try {
            Map result = service.getAccessControlAppsInfo(type, userId);
            return result == null ? new HashMap<>() : result;
        } catch (RemoteException e) {
            clearService();
            Log.w(TAG, "getAccessControlAppsInfo failed", e);
        }
        return new HashMap<>();
    }

    public void setAccessControlEnabled(String type, boolean enable, int userId) {
        IOplusAccessControlManager service = getService();
        if (service == null) {
            return;
        }
        try {
            service.setAccessControlEnabled(type, enable, userId);
        } catch (RemoteException e) {
            clearService();
            Log.w(TAG, "setAccessControlEnabled failed", e);
        }
    }

    public boolean getAccessControlEnabled(String type, int userId) {
        IOplusAccessControlManager service = getService();
        if (service == null) {
            return false;
        }
        try {
            return service.getAccessControlEnabled(type, userId);
        } catch (RemoteException e) {
            clearService();
            Log.w(TAG, "getAccessControlEnabled failed", e);
        }
        return false;
    }

    public void addEncryptPass(String packageName, int windowMode, int userId) {
        IOplusAccessControlManager service = getService();
        if (service == null) {
            return;
        }
        try {
            service.addEncryptPass(packageName, windowMode, userId);
        } catch (RemoteException e) {
            clearService();
            Log.w(TAG, "addEncryptPass failed", e);
        }
    }

    public boolean isEncryptPass(String packageName, int userId) {
        IOplusAccessControlManager service = getService();
        if (service == null) {
            return false;
        }
        try {
            return service.isEncryptPass(packageName, userId);
        } catch (RemoteException e) {
            clearService();
            Log.w(TAG, "isEncryptPass failed", e);
        }
        return false;
    }

    public boolean isEncryptedPackage(String packageName, int userId) {
        IOplusAccessControlManager service = getService();
        if (service == null) {
            return false;
        }
        try {
            return service.isEncryptedPackage(packageName, userId);
        } catch (RemoteException e) {
            clearService();
            Log.w(TAG, "isEncryptedPackage failed", e);
        }
        return false;
    }

    public void setPrivacyAppsInfoForUser(Map<String, Integer> privacyInfo, boolean enabled, int userId) {
        setAccessControlAppsInfo(TYPE_ENCRYPT, privacyInfo, userId);
        setAccessControlEnabled(TYPE_ENCRYPT, enabled, userId);
    }

    public boolean getApplicationAccessControlEnabledAsUser(String packageName, int userId) {
        return isEncryptedPackage(packageName, userId);
    }

    public void addAccessControlPassForUser(String packageName, int windowMode, int userId) {
        addEncryptPass(packageName, windowMode, userId);
    }

    public Map<String, Integer> getPrivacyAppInfo(int userId) {
        return getAccessControlAppsInfo(TYPE_ENCRYPT, userId);
    }

    public boolean isAccessControlPassForUser(String packageName, int userId) {
        return isEncryptPass(packageName, userId);
    }
}
