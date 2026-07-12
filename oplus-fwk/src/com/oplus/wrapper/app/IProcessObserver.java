package com.oplus.wrapper.app;

import android.os.RemoteException;

public interface IProcessObserver {
    void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities)
            throws RemoteException;

    void onForegroundServicesChanged(int pid, int uid, int serviceTypes) throws RemoteException;

    void onProcessDied(int pid, int uid) throws RemoteException;

    abstract class Stub implements IProcessObserver {
    }
}
