package com.oplus.os;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.storage.DiskInfo;
import android.os.storage.IStorageManager;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OplusUsbEnvironment extends Environment {
    private static final String TAG = "OppoUsbEnvironmentSys";

    private static final String DEFAULT_INTERNAL_PATH = "/storage/emulated/0";
    private static final String MULTIAPP_INTERNAL_PATH = "/storage/emulated/999";

    public static final String FILE_SEPARATOR = "/";

    public static final int NONE = -1;
    public static final int INTERNAL = 1;
    public static final int EXTERNAL = 2;
    public static final int OTG = 3;

    private static boolean sInited = false;
    private static boolean sListenerRegistered = false;

    private static String sInternalSdDir = DEFAULT_INTERNAL_PATH;
    private static String sExternalSdDir = null;
    private static final ArrayList<String> sOtgPathes = new ArrayList<>();

    private static IStorageManager sMountService = null;
    private static final Object sLock = new Object();

    private static final BroadcastReceiver sVolumeStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            synchronized (sLock) {
                if (intent == null) {
                    return;
                }

                String action = intent.getAction();
                int state = intent.getIntExtra("android.os.storage.extra.VOLUME_STATE", -1);
                String id = intent.getStringExtra("android.os.storage.extra.VOLUME_ID");

                if (state == VolumeInfo.STATE_MOUNTED || state == VolumeInfo.STATE_UNMOUNTED) {
                    Log.d(TAG, "onReceive: action=" + action + ", state=" + state + ", id=" + id);
                    getVolumesLocked();
                }
            }
        }
    };

    private static final StorageEventListener sStorageListener = new StorageEventListener() {
        @Override
        public void onVolumeStateChanged(VolumeInfo vol, int oldState, int newState) {
            synchronized (sLock) {
                if (vol == null) {
                    return;
                }

                DiskInfo diskInfo = vol.getDisk();
                if (diskInfo == null) {
                    return;
                }

                String path = vol.path;
                if (oldState != VolumeInfo.STATE_MOUNTED && newState == VolumeInfo.STATE_MOUNTED) {
                    if (diskInfo.isSd() && path != null) {
                        sExternalSdDir = path;
                        Log.d(TAG, "onVolumeStateChanged: sd mount. sExternalSdDir=" + sExternalSdDir);
                    }

                    if (diskInfo.isUsb() && path != null && !sOtgPathes.contains(path)) {
                        sOtgPathes.add(path);
                        Log.d(TAG, "onVolumeStateChanged: sOtgPathes.add=" + path);
                    }
                } else if (newState != VolumeInfo.STATE_MOUNTED && oldState == VolumeInfo.STATE_MOUNTED) {
                    if (diskInfo.isSd()) {
                        sExternalSdDir = null;
                        Log.d(TAG, "onVolumeStateChanged: sd unmount. sExternalSdDir=" + sExternalSdDir);
                    }

                    if (diskInfo.isUsb() && path != null && sOtgPathes.contains(path)) {
                        sOtgPathes.remove(path);
                        Log.d(TAG, "onVolumeStateChanged: sOtgPathes.remove=" + path);
                    }
                }
            }
        }
    };

    private OplusUsbEnvironment() {
    }

    private static void update(Context context) {
        synchronized (sLock) {
            if (sMountService == null) {
                sMountService = IStorageManager.Stub.asInterface(ServiceManager.getService("mount"));
            }

            if (!sInited) {
                sInited = true;
                getVolumesLocked();
            }

            if (context == null || sListenerRegistered) {
                return;
            }

            Context appContext = context.getApplicationContext();
            if (appContext == null) {
                appContext = context;
            }

            boolean hasWriteMediaStorage =
                    context.checkSelfPermission("android.permission.WRITE_MEDIA_STORAGE") == 0;

            if (hasWriteMediaStorage) {
                try {
                    IntentFilter filter = new IntentFilter();
                    filter.addAction("android.os.storage.action.VOLUME_STATE_CHANGED");
                    appContext.registerReceiver(sVolumeStateReceiver, filter);
                    sListenerRegistered = true;
                    Log.d(TAG, "update: registered volume state receiver");
                    return;
                } catch (Throwable t) {
                    Log.w(TAG, "update: failed to register volume receiver", t);
                }
            }

            try {
                StorageManager storageManager =
                        (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                if (storageManager != null) {
                    storageManager.registerListener(sStorageListener);
                    sListenerRegistered = true;
                    Log.d(TAG, "update: registered storage listener");
                }
            } catch (Throwable t) {
                Log.w(TAG, "update: failed to register storage listener", t);
            }
        }
    }

    public static File getInternalSdDirectory(Context context) {
        String path = getInternalPath(context);
        return path == null ? null : new File(path);
    }

    public static File getExternalSdDirectory(Context context) {
        String path = getExternalPath(context);
        return path == null ? null : new File(path);
    }

    public static String getInternalSdState(Context context) {
        return getVolumeState(context, getInternalPath(context));
    }

    public static String getExternalSdState(Context context) {
        return getVolumeState(context, getExternalPath(context));
    }

    public static boolean isExternalSDRemoved(Context context) {
        String path = getExternalPath(context);
        if (path == null) {
            return true;
        }

        String state = getVolumeState(context, path);
        Log.i(TAG, "isExternalSDRemoved: the state of volume is: " + state);
        return Environment.MEDIA_REMOVED.equals(state);
    }

    public static boolean isNestMounted() {
        synchronized (sLock) {
            if (sInternalSdDir == null || sExternalSdDir == null) {
                return false;
            }

            return sInternalSdDir.startsWith(sExternalSdDir)
                    || sExternalSdDir.startsWith(sInternalSdDir);
        }
    }

    public static List<String> getOtgPath(Context context) {
        update(context);

        synchronized (sLock) {
            return new ArrayList<>(sOtgPathes);
        }
    }

    public static boolean isVolumeMounted(Context context, String path) {
        return Environment.MEDIA_MOUNTED.equals(getVolumeState(context, path));
    }

    public static String getInternalPath(Context context) {
        update(context);

        synchronized (sLock) {
            if (sInternalSdDir != null && !sInternalSdDir.isEmpty()) {
                return sInternalSdDir;
            }
        }

        File dir = Environment.getExternalStorageDirectory();
        String path = dir == null ? null : dir.getAbsolutePath();
        return path == null || path.isEmpty() ? DEFAULT_INTERNAL_PATH : path;
    }

    public static String getExternalPath(Context context) {
        update(context);

        synchronized (sLock) {
            return sExternalSdDir;
        }
    }

    public static int getPathType(Context context, String path) {
        if (path == null) {
            return NONE;
        }

        update(context);

        synchronized (sLock) {
            if (path.equals(sInternalSdDir)) {
                return INTERNAL;
            }

            if (path.equals(sExternalSdDir)) {
                return EXTERNAL;
            }

            if (sOtgPathes.contains(path)) {
                return OTG;
            }

            return NONE;
        }
    }

    public static String getMultiappSdDirectory() {
        return MULTIAPP_INTERNAL_PATH;
    }

    private static String getVolumeState(Context context, String path) {
        if (context == null || path == null) {
            return Environment.MEDIA_UNKNOWN;
        }

        update(context);

        StorageManager storageManager =
                (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        if (storageManager == null) {
            return Environment.MEDIA_UNKNOWN;
        }

        return storageManager.getVolumeState(path);
    }

    private static void getVolumesLocked() {
        if (sMountService == null) {
            Log.e(TAG, "getVolumesLocked: sMountService is null");
            return;
        }

        try {
            VolumeInfo[] volumes = sMountService.getVolumes(0);

            sExternalSdDir = null;
            sOtgPathes.clear();

            if (volumes == null) {
                return;
            }

            for (VolumeInfo volume : volumes) {
                if (volume == null) {
                    continue;
                }

                String path = volume.path;

                if (volume.type == VolumeInfo.TYPE_EMULATED) {
                    int userId = UserHandle.myUserId();
                    if (path != null) {
                        sInternalSdDir = path.concat(FILE_SEPARATOR).concat(Integer.toString(userId));
                        Log.d(TAG, "getVolumesLocked: sInternalSdDir=" + sInternalSdDir);
                    }
                    continue;
                }

                DiskInfo diskInfo = volume.getDisk();
                if (diskInfo == null || path == null) {
                    continue;
                }

                if (diskInfo.isSd()) {
                    sExternalSdDir = path;
                    Log.d(TAG, "getVolumesLocked: sExternalSdDir=" + sExternalSdDir);
                }

                if (diskInfo.isUsb() && !sOtgPathes.contains(path)) {
                    sOtgPathes.add(path);
                    Log.d(TAG, "getVolumesLocked: sOtgPathes.add=" + path);
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getVolumesLocked: failed to query volumes", e);
        } catch (Throwable t) {
            Log.e(TAG, "getVolumesLocked: unexpected failure", t);
        }
    }
}
