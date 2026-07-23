/*
 * Copyright (C) 2024 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.app;

import android.content.Context;

/**
 * Minimal stub of Oplus' Hans (app-freeze) manager.
 *
 * OplusCamera's CommonUtil calls
 *   OplusHansFreezeManager.getInstance().requestFastFreeze(context, uid, reason)
 * to freeze background apps while capturing. Without OplusHansManagerService this
 * class is absent on AOSP, so the class load throws NoClassDefFoundError and the
 * camera crashes on mode switch (BaseMode.C7 -> CommonUtil). The freeze is only a
 * power/scheduling optimisation, so a no-op that reports success is safe.
 */
public class OplusHansFreezeManager {

    private static volatile OplusHansFreezeManager sInstance;

    public static OplusHansFreezeManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusHansFreezeManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusHansFreezeManager();
                }
            }
        }
        return sInstance;
    }

    /** No-op: returns 0 (success) without freezing anything. */
    public int requestFastFreeze(Context context, int uid, String reason) {
        return 0;
    }
}
