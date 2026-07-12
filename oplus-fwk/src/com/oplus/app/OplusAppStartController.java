package com.oplus.app;

import android.os.IBinder;

public class OplusAppStartController implements IOplusAppStartController {
    public OplusAppStartController() {
    }

    @Override
    public IBinder asBinder() {
        return null;
    }

    public void appStartMonitor(
            String pkgName, String exceptionClass, String exceptionMsg, String exceptionTrace,
            String monitorType) {
    }

    public void preventStartMonitor(
            String callerPkg, String calledPkg, String startMode, String preventMode,
            String reason) {
    }

    public void notifyPreventIndulge(String pkgName) {
    }
}
