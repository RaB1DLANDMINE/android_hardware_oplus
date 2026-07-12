package com.oplus.wrapper.content.pm;

import android.os.RemoteException;

public interface IPackageDataObserver {
    void onRemoveCompleted(String packageName, boolean succeeded) throws RemoteException;

    abstract class Stub implements IPackageDataObserver {
    }
}
