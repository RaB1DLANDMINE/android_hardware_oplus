package com.oplus.view;

import android.content.Context;

public class JankManager {
    private static final JankManager INSTANCE = new JankManager();

    public static JankManager getInstance() {
        return INSTANCE;
    }

    public void gfxSceneBegin(Context context, int scene, String sceneDes, long policy) {}

    public void gfxSceneEnd(Context context, int scene) {}
}
