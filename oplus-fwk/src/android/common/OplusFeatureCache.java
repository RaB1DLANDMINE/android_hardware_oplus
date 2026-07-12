/*
 * Copyright (C) 2026 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package android.common;

import android.util.Slog;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache for OPlus feature extension defaults.
 *
 * @hide
 */
public final class OplusFeatureCache {
    private static final String TAG = "OplusFeatureCache";

    private static final ConcurrentHashMap<OplusFeatureList.OplusIndex, IOplusCommonFeature>
            sFeatureCache = new ConcurrentHashMap<>();

    private OplusFeatureCache() {
    }

    public static <T extends IOplusCommonFeature> T get(T def) {
        return getOrCreate(def);
    }

    public static <T extends IOplusCommonFeature> T getOrCreate(T def, Object... vars) {
        verifyDefaultFeature(def);

        IOplusCommonFeature cached = sFeatureCache.get(def.index());
        if (cached != null) {
            return (T) cached;
        }

        T feature = createFeature(def, vars);
        IOplusCommonFeature old = sFeatureCache.putIfAbsent(def.index(), feature);
        return (T) (old != null ? old : feature);
    }

    private static <T extends IOplusCommonFeature> T createFeature(T def, Object... vars) {
        try {
            return OplusFrameworkFactory.getInstance().getFeature(def, vars);
        } catch (Throwable t) {
            Slog.w(TAG, "Failed to create feature " + def.index(), t);
            return def;
        }
    }

    private static void verifyDefaultFeature(IOplusCommonFeature def) {
        if (def == null) {
            throw new IllegalArgumentException("def can not be null");
        }
        if (def.index() == OplusFeatureList.OplusIndex.End) {
            throw new IllegalArgumentException(def + " must override index() method");
        }
    }
}
