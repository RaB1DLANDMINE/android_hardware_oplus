/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.content;

import java.util.List;

public interface IOplusFeatureConfigManager {
    enum FeatureID {
        STATIC_COMPONENT,
        DYNAMIC_SIMSLOT_1,
        DYNAMIC_SIMSLOT_2,
        INVALID
    }

    default boolean hasFeature(String featureName) {
        return false;
    }

    default boolean hasFeature(String featureName, FeatureID featureID) {
        return false;
    }

    default boolean enableFeature(String featureName) {
        return false;
    }

    default boolean disableFeature(String featureName) {
        return false;
    }

    default boolean enableFeature(String featureName, FeatureID featureID) {
        return false;
    }

    default boolean disableFeature(String featureName, FeatureID featureID) {
        return false;
    }

    default void notifyFeaturesUpdate(String action, String actionValue) {
    }

    default void notifyFeaturesUpdate(String action, String actionValue, FeatureID featureID) {
    }

    default boolean registerFeatureObserver(List<String> features, IOplusFeatureObserver observer) {
        return false;
    }

    default boolean unregisterFeatureObserver(IOplusFeatureObserver observer) {
        return false;
    }

    default boolean registerFeatureObserver(List<String> features, FeatureID featureID, IOplusFeatureMapObserver observer) {
        return false;
    }

    default boolean unregisterFeatureObserver(FeatureID featureID, IOplusFeatureMapObserver observer) {
        return false;
    }

    default boolean registerFeatureActionObserver(IOplusFeatureActionObserver observer) {
        return false;
    }

    default boolean unregisterFeatureActionObserver(IOplusFeatureActionObserver observer) {
        return false;
    }
}
