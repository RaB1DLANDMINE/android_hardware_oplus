package com.oplus.wrapper.app;

import android.os.RemoteException;

public interface IUserSwitchObserver {
    @Deprecated
    void onBeforeUserSwitching(int newUserId) throws RemoteException;

    void onBeforeUserSwitching(int newUserId, com.oplus.wrapper.os.IRemoteCallback reply)
            throws RemoteException;

    void onForegroundProfileSwitch(int newProfileId) throws RemoteException;

    void onLockedBootComplete(int newUserId) throws RemoteException;

    void onUserSwitchComplete(int newUserId) throws RemoteException;

    void onUserSwitching(int newUserId, com.oplus.wrapper.os.IRemoteCallback reply)
            throws RemoteException;

    abstract class Stub implements IUserSwitchObserver {
    }
}
