package com.oplus.wrapper.view;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IRotationWatcher {
    void onRotationChanged(int rotation) throws RemoteException;

    abstract class Stub implements IInterface, IRotationWatcher {
        private final android.view.IRotationWatcher mRotationWatcher;

        public Stub(android.view.IRotationWatcher rotationWatcher) {
            this.mRotationWatcher = rotationWatcher;
        }

        public static com.oplus.wrapper.view.IRotationWatcher asInterface(IBinder obj) {
            android.view.IRotationWatcher watcher = android.view.IRotationWatcher.Stub.asInterface(obj);
            if (watcher == null) {
                return null;
            }
            return new Proxy(watcher);
        }

        @Override
        public IBinder asBinder() {
            return this.mRotationWatcher != null ? this.mRotationWatcher.asBinder() : null;
        }

        private static class Proxy implements com.oplus.wrapper.view.IRotationWatcher {
            private final android.view.IRotationWatcher mRotationWatcher;

            Proxy(android.view.IRotationWatcher rotationWatcher) {
                this.mRotationWatcher = rotationWatcher;
            }

            @Override
            public void onRotationChanged(int rotation) throws RemoteException {
                this.mRotationWatcher.onRotationChanged(rotation);
            }
        }
    }
}
