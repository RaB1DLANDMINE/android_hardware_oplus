package com.oplus.settings;

import android.content.Context;
import android.net.Uri;
import android.os.Process;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class OplusBaseSettings {
    private static final String BASE_URI = "content://OplusSettings";

    public static boolean isSystemProcess() {
        return Process.myUid() == Process.SYSTEM_UID;
    }

    public static InputStream readConfigAsUser(
            Context context, String customPath, int userId, int type) throws IOException {
        if (customPath == null || customPath.isEmpty()) {
            return null;
        }
        try {
            if (context != null) {
                Uri uri = OplusSettingsConfig.getUri(BASE_URI, customPath, userId, type);
                InputStream stream = context.getContentResolver().openInputStream(uri);
                if (stream != null) {
                    return stream;
                }
            }
        } catch (Throwable ignored) {
        }
        return new FileInputStream(OplusSettingsConfig.getFilePath(type, userId, customPath));
    }

    public static String readConfigStringAsUser(
            Context context, String customPath, int userId, int type) throws IOException {
        InputStream stream = readConfigAsUser(context, customPath, userId, type);
        if (stream == null) {
            return null;
        }
        try (InputStream in = stream; ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            return out.toString(StandardCharsets.UTF_8.name());
        }
    }

    public static OutputStream writeConfigAsUser(
            Context context, String customPath, int userId, int type) throws IOException {
        if (customPath == null || customPath.isEmpty()) {
            return null;
        }
        try {
            if (context != null) {
                Uri uri = OplusSettingsConfig.getUri(BASE_URI, customPath, userId, type);
                OutputStream stream = context.getContentResolver().openOutputStream(uri);
                if (stream != null) {
                    return stream;
                }
            }
        } catch (Throwable ignored) {
        }
        return new FileOutputStream(OplusSettingsConfig.getFilePath(type, userId, customPath));
    }

    public static int writeConfigStringAsUser(
            Context context, String customPath, int userId, int type, String value) throws IOException {
        byte[] data = value == null ? new byte[0] : value.getBytes(StandardCharsets.UTF_8);
        try (OutputStream out = writeConfigAsUser(context, customPath, userId, type)) {
            if (out == null) {
                return -2;
            }
            out.write(data);
            return data.length;
        }
    }

    public static void registerChangeListenerAsUser(
            Context context, String customPath, int userId, int type,
            OplusSettingsChangeListener listener) {
        if (context == null || listener == null) {
            return;
        }
        Uri uri = OplusSettingsConfig.getUri(BASE_URI, customPath, userId, type);
        context.getContentResolver().registerContentObserver(uri, true, listener);
    }

    public static void registerChangeListenerForAll(
            Context context, String customPath, int type, OplusSettingsChangeListener listener) {
        registerChangeListenerAsUser(context, customPath, -1, type, listener);
    }
}
