/*
 * Copyright (C) 2026 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.id.impl;

import android.app.Activity;
import android.app.ActivityThread;
import android.app.OplusNotificationManager;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class IdProviderImpl {
    private static final String ACTION_REQUEST_OAID_PERMISSION =
            "com.oplus.omes.REQUEST_OAID_PERMISSION";
    private static final String ANDROID_REQUEST_PERMISSIONS_WHO = "@android:requestPermissions:";
    private static final String CLASSNAME_GRANT_OAID_PERM_ACTIVITY =
            "com.heytap.openid.oaidcontrolled.view.GrantOAIDPermissionActivity";
    private static final String OMES_CHECK_OAID_PERMISSION_METHOD = "checkSelfOAIDPermission";
    private static final String OMES_CHECK_OAID_RESULT_KEY = "oaidStatus";
    private static final String OMES_GET_OAID_METHOD = "OUID";
    private static final Uri OMES_OAID_STATUS_URI =
            Uri.parse("content://com.oplus.omes.oaid_status_provider");
    private static final String OPENID_PKG_NAME = "com.heytap.openid";
    private static final String PERMISSION_OAID_NAME = "com.oplus.permission.OBTAIN_OAID";
    private static final String TAG = "IdProviderImpl";

    public String getStdid(Context context, String type) {
        if (OMES_GET_OAID_METHOD.equals(type)) {
            return getOUIDInner(context);
        }

        String pkg = context.getPackageName();
        int uid = Binder.getCallingUid();
        try {
            OplusNotificationManager onm = new OplusNotificationManager();
            String id = onm.getStdid(pkg, uid, type);
            if (!TextUtils.isEmpty(id)) {
                return id;
            }
        } catch (RuntimeException e) {
            Log.w(TAG, "get stdid fallback for " + type + ": " + e.getMessage());
        }

        return getLocalStdid(pkg, uid, type);
    }

    @Deprecated
    public String getGUID(Context context) {
        return getStdid(context, "GUID");
    }

    @Deprecated
    public String getOUID(Context context) {
        return getStdid(context, OMES_GET_OAID_METHOD);
    }

    @Deprecated
    public String getDUID(Context context) {
        return getStdid(context, "DUID");
    }

    @Deprecated
    public String getAUID(Context context) {
        return getStdid(context, "AUID");
    }

    public String getAPID(Context context) {
        return getStdid(context, "APID");
    }

    @Deprecated
    public boolean checkGetStdid(Context context, String type) {
        try {
            OplusNotificationManager onm = new OplusNotificationManager();
            return onm.checkGetStdid(context.getPackageName(), Binder.getCallingUid(), type);
        } catch (RuntimeException e) {
            Log.w(TAG, "check stdid fallback for " + type + ": " + e.getMessage());
            return isSupportedStdid(type);
        }
    }

    @Deprecated
    public boolean checkGetGUID(Context context) {
        return checkGetStdid(context, "GUID");
    }

    @Deprecated
    public boolean checkGetAPID(Context context) {
        return checkGetStdid(context, "APID");
    }

    public static int checkSelfOAIDPermission(Context context) {
        try (ContentProviderClient client =
                context.getContentResolver()
                        .acquireUnstableContentProviderClient(OMES_OAID_STATUS_URI)) {
            if (client == null) {
                Log.w(TAG, "check oaid failed: client is null.");
                return -2;
            }

            Bundle result = client.call(OMES_CHECK_OAID_PERMISSION_METHOD, null, null);
            if (result == null) {
                Log.w(TAG, "check oaid failed: result is null.");
                return -2;
            }

            return result.getInt(OMES_CHECK_OAID_RESULT_KEY, -1);
        } catch (RemoteException e) {
            Log.w(TAG, "check oaid remote exception: " + e.getMessage());
            return -2;
        }
    }

    public static void requestOAIDPermission(Activity activity, int requestCode) {
        Intent intent = new Intent(ACTION_REQUEST_OAID_PERMISSION);
        intent.putExtra(
                "android.content.pm.extra.REQUEST_PERMISSIONS_NAMES",
                new String[] {PERMISSION_OAID_NAME});
        intent.putExtra("android.intent.extra.UID", Process.myUid());
        intent.setClassName(OPENID_PKG_NAME, CLASSNAME_GRANT_OAID_PERM_ACTIVITY);

        if (activity.getPackageManager().resolveActivity(intent, 65536) == null) {
            Log.w(TAG, "can not resolve oaid activity");
            return;
        }

        activity.startActivityForResult(ANDROID_REQUEST_PERMISSIONS_WHO, intent, requestCode, null);
    }

    @Deprecated
    public String getOpenid(Context context, String type) {
        return getStdid(context, type);
    }

    @Deprecated
    public boolean checkGetOpenid(Context context, String type) {
        return checkGetStdid(context, type);
    }

    private static String getOUIDInner(Context context) {
        if (OPENID_PKG_NAME.equals(ActivityThread.currentPackageName())) {
            try {
                OplusNotificationManager onm = new OplusNotificationManager();
                String id = onm.getStdid(
                        context.getPackageName(), Binder.getCallingUid(), OMES_GET_OAID_METHOD);
                if (!TextUtils.isEmpty(id)) {
                    return id;
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "get ouid fallback: " + e.getMessage());
            }
        }

        return getOUIDByOpenIDApk(context);
    }

    private static String getOUIDByOpenIDApk(Context context) {
        try (ContentProviderClient client =
                context.getContentResolver()
                        .acquireUnstableContentProviderClient(OMES_OAID_STATUS_URI)) {
            if (client == null) {
                Log.w(TAG, "get oaid failed: client is null.");
                return "";
            }

            Bundle result = client.call(OMES_GET_OAID_METHOD, null, new Bundle());
            if (result == null) {
                Log.w(TAG, "get oaid failed: result is null.");
                return "";
            }

            return result.getString(OMES_GET_OAID_METHOD, "");
        } catch (RemoteException e) {
            Log.w(TAG, "get oaid remote exception: " + e.getMessage());
            return "";
        }
    }

    private static boolean isSupportedStdid(String type) {
        return "GUID".equals(type)
                || "APID".equals(type)
                || "AUID".equals(type)
                || "DUID".equals(type)
                || OMES_GET_OAID_METHOD.equals(type);
    }

    private static String getLocalStdid(String pkg, int uid, String type) {
        if (!isSupportedStdid(type)) {
            return "";
        }

        String scope = "GUID".equals(type) ? "android" : pkg + ":" + uid;
        return sha256("oplus-stdid:" + type + ":" + Build.FINGERPRINT + ":" + scope)
                .substring(0, 32)
                .toUpperCase(Locale.ROOT);
    }

    private static String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                sb.append(String.format(Locale.ROOT, "%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
