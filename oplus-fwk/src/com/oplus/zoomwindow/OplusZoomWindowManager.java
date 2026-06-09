package com.oplus.zoomwindow;

public class OplusZoomWindowManager {
    private static final OplusZoomWindowManager INSTANCE = new OplusZoomWindowManager();

    public static OplusZoomWindowManager getInstance() {
        return INSTANCE;
    }

    public OplusZoomWindowInfo getCurrentZoomWindowState() {
        return new OplusZoomWindowInfo();
    }

    public boolean isSupportZoomWindowMode() {
        return false;
    }

    public boolean registerZoomWindowObserver(IOplusZoomWindowObserver observer) {
        return true;
    }

    public boolean unregisterZoomWindowObserver(IOplusZoomWindowObserver observer) {
        return true;
    }
}
