package com.oplus.settings;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OplusSettings extends OplusBaseSettings {
    public static final int TYPE_OPLUS = 0;

    public OplusSettings() {
    }

    public static InputStream readConfig(Context context, String customPath, int type)
            throws IOException {
        return readConfigAsUser(context, customPath, -2, type);
    }

    public static OutputStream writeConfig(Context context, String customPath, int type)
            throws IOException {
        return writeConfigAsUser(context, customPath, -2, type);
    }

    public static String readConfigString(Context context, String customPath, int type)
            throws IOException {
        return readConfigStringAsUser(context, customPath, -2, type);
    }

    public static int writeConfigString(
            Context context, String customPath, int type, String value) throws IOException {
        return writeConfigStringAsUser(context, customPath, -2, type, value);
    }

    public static void registerChangeListener(
            Context context, String customPath, int type, OplusSettingsChangeListener listener) {
        registerChangeListenerAsUser(context, customPath, -2, type, listener);
    }

    public static void unRegisterChangeListener(
            Context context, OplusSettingsChangeListener listener) {
        if (context != null && listener != null) {
            context.getContentResolver().unregisterContentObserver(listener);
        }
    }
}
