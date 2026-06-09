package com.oplus.settings;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

public abstract class OplusSettingsChangeListener extends ContentObserver {
    public OplusSettingsChangeListener(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        String path = uri != null ? uri.getPath() : null;
        onSettingsChange(selfChange, path, android.os.UserHandle.myUserId());
    }

    public abstract void onSettingsChange(boolean selfChange, String path, int userId);
}
