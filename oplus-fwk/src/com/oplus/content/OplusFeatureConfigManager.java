/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.content;

import android.text.TextUtils;

import java.util.List;

public class OplusFeatureConfigManager implements IOplusFeatureConfigManager {
    public static OplusFeatureConfigManager sInstance = null;

    public static OplusFeatureConfigManager getInstance() {
        if (sInstance == null) {
            sInstance = new OplusFeatureConfigManager();
        }
        return sInstance;
    }

    public boolean hasFeature(String featureName) {
        return false;
    }

    public boolean hasFeature(String name, FeatureID featureID) {
        return false;
    }

    public boolean enableFeature(String featureName) {
        return false;
    }

    public boolean disableFeature(String featureName) {
        return false;
    }

    public boolean enableFeature(String featureName, FeatureID featureID) {
        return false;
    }

    public boolean disableFeature(String featureName, FeatureID featureID) {
        return false;
    }

    public void notifyFeaturesUpdate(String action, String actionValue) {
    }

    public void notifyFeaturesUpdate(String action, String actionValue, FeatureID featureID) {
    }

    public boolean registerFeatureObserver(List<String> features, OnFeatureObserver observer) {
        return false;
    }

    public boolean unregisterFeatureObserver(OnFeatureObserver observer) {
        return false;
    }

    public boolean registerFeatureObserver(List<String> features, FeatureID featureID, OnFeatureMapObserver observer) {
        return false;
    }

    public boolean unregisterFeatureObserver(FeatureID featureID, OnFeatureMapObserver observer) {
        return false;
    }

    public boolean registerFeatureActionObserver(OnFeatureActionObserver observer) {
        return false;
    }

    public boolean unregisterFeatureActionObserver(OnFeatureActionObserver observer) {
        return false;
    }

    public interface OnFeatureObserver {
        default void onFeatureUpdate(List<String> features) {}
    }

    public interface OnFeatureMapObserver {
        default void onFeatureUpdate(List<String> features, FeatureID featureID) {}
    }

    public interface OnFeatureActionObserver {
        default void onFeaturesActionUpdate(String action, String actionValue, FeatureID featureID) {}
    }
}
