package com.oplus.zoomwindow;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IOplusZoomWindowObserver extends IInterface {
    void onZoomWindowShow(OplusZoomWindowInfo info) throws RemoteException;

    void onZoomWindowHide(OplusZoomWindowInfo info) throws RemoteException;

    void onZoomWindowDied(String appName) throws RemoteException;

    void onInputMethodChanged(boolean isShown) throws RemoteException;

    abstract class Stub extends Binder implements IOplusZoomWindowObserver {
        public Stub() {
            attachInterface(this, "com.oplus.zoomwindow.IOplusZoomWindowObserver");
        }

        public static IOplusZoomWindowObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface local = obj.queryLocalInterface("com.oplus.zoomwindow.IOplusZoomWindowObserver");
            if (local instanceof IOplusZoomWindowObserver) {
                return (IOplusZoomWindowObserver) local;
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        private static class Proxy implements IOplusZoomWindowObserver {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void onZoomWindowShow(OplusZoomWindowInfo info) {
            }

            @Override
            public void onZoomWindowHide(OplusZoomWindowInfo info) {
            }

            @Override
            public void onZoomWindowDied(String appName) {
            }

            @Override
            public void onInputMethodChanged(boolean isShown) {
            }
        }
    }
}
