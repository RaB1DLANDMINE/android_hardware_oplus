package com.oplus.wrapper.os;

import android.os.Bundle;
import android.os.RemoteException;

public interface IRemoteCallback {
    void sendResult(Bundle data) throws RemoteException;

    abstract class Stub implements IRemoteCallback {
        public static IRemoteCallback asInterface(android.os.IRemoteCallback callback) {
            if (callback == null) {
                return null;
            }
            return callback::sendResult;
        }
    }
}
