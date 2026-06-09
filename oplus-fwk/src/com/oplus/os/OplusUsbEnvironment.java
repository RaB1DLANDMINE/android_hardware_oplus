package com.oplus.os;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OplusUsbEnvironment extends Environment {
    private static final String DEFAULT_INTERNAL_PATH = "/storage/emulated/0";
    private static final String MULTIAPP_INTERNAL_PATH = "/storage/emulated/999";

    public static final int NONE = -1;
    public static final int INTERNAL = 1;
    public static final int EXTERNAL = 2;
    public static final int OTG = 3;

    private OplusUsbEnvironment() {
    }

    public static File getInternalSdDirectory(Context context) {
        return new File(getInternalPath(context));
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
        return path == null || "removed".equals(getVolumeState(context, path));
    }

    public static boolean isNestMounted() {
        return false;
    }

    public static List<String> getOtgPath(Context context) {
        return new ArrayList<>();
    }

    public static boolean isVolumeMounted(Context context, String path) {
        return "mounted".equals(getVolumeState(context, path));
    }

    public static String getInternalPath(Context context) {
        File dir = Environment.getExternalStorageDirectory();
        String path = dir == null ? null : dir.getAbsolutePath();
        return path == null || path.isEmpty() ? DEFAULT_INTERNAL_PATH : path;
    }

    public static String getExternalPath(Context context) {
        return null;
    }

    public static int getPathType(Context context, String path) {
        if (path == null) {
            return NONE;
        }
        if (path.equals(getInternalPath(context))) {
            return INTERNAL;
        }
        String externalPath = getExternalPath(context);
        if (path.equals(externalPath)) {
            return EXTERNAL;
        }
        return NONE;
    }

    public static String getMultiappSdDirectory() {
        return MULTIAPP_INTERNAL_PATH;
    }

    private static String getVolumeState(Context context, String path) {
        if (context == null || path == null) {
            return Environment.MEDIA_UNKNOWN;
        }
        StorageManager storageManager = context.getSystemService(StorageManager.class);
        if (storageManager == null) {
            return Environment.MEDIA_UNKNOWN;
        }
        return storageManager.getVolumeState(path);
    }
}
