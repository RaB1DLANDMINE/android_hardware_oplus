package com.oplus.devicestate;

import java.util.concurrent.Executor;

/**
 * Stub for the OEM device-state manager used by OplusCamera's
 * OplusDeviceStateManagerCompat (o8.d). On stock this lives in oplus-framework.jar
 * (bootclasspath). We ship it off-boot via oplus.camera.stubs. The app instantiates
 * it in a try/catch and only queries the current/requested device state, which on a
 * non-foldable slab device is always the default (0).
 */
public class OplusDeviceStateManager {

    public interface FoldStateListener {
        void onStateChanged(int oldState, int newState);
    }

    public OplusDeviceStateManager() {
    }

    public int getDeviceState() {
        return 0;
    }

    public int requestDeviceState(int state) {
        return 0;
    }

    public int cancelDeviceStateRequest(int requestId) {
        return 0;
    }

    // Short-named accessors the app calls on this manager; return empty (no state info).
    public String a() {
        return "";
    }

    public String b() {
        return "";
    }

    public String c() {
        return "";
    }

    public void registerFoldStateListener(FoldStateListener listener) {
    }

    public void registerFoldStateListener(FoldStateListener listener, Executor executor) {
    }

    public void unregisterFoldStateListener(FoldStateListener listener) {
    }
}
