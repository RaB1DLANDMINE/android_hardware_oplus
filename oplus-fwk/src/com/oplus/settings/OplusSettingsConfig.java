package com.oplus.settings;

import android.net.Uri;

public final class OplusSettingsConfig {
    private OplusSettingsConfig() {
    }

    public static String getFilePath(int type, int userId, String customPath) {
        return customPath;
    }

    public static Uri getUri(String baseUri, String customPath, int userId, int type) {
        return Uri.parse(baseUri).buildUpon()
                .appendEncodedPath(customPath == null ? "" : customPath)
                .appendQueryParameter("ParamsUserId", String.valueOf(userId))
                .appendQueryParameter("ParamsType", String.valueOf(type))
                .build();
    }
}
