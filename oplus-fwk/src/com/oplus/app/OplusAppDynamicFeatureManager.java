package com.oplus.app;

public class OplusAppDynamicFeatureManager {
    private static final com.oplus.app.OplusAppDynamicFeatureManager INSTANCE = new com.oplus.app.OplusAppDynamicFeatureManager();
    public OplusAppDynamicFeatureManager() {}
    public static com.oplus.app.OplusAppDynamicFeatureManager getInstance() { return INSTANCE; }
}
