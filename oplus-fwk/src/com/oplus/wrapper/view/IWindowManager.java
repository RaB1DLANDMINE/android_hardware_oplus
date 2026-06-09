package com.oplus.wrapper.view;

import android.graphics.Point;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IWindowManager extends IInterface {
    int getInitialDisplayDensity(int displayId) throws RemoteException;

    void getInitialDisplaySize(int displayId, Point outSize) throws RemoteException;

    int getBaseDisplayDensity(int displayId) throws RemoteException;

    void getBaseDisplaySize(int displayId, Point outSize) throws RemoteException;

    boolean hasNavigationBar(int displayId) throws RemoteException;

    abstract class Stub {
        public static IWindowManager asInterface(IBinder obj) {
            android.view.IWindowManager windowManager = android.view.IWindowManager.Stub.asInterface(obj);
            return new Proxy(windowManager);
        }

        private static class Proxy implements IWindowManager {
            private final android.view.IWindowManager mWindowManager;

            Proxy(android.view.IWindowManager windowManager) {
                mWindowManager = windowManager;
            }

            @Override
            public IBinder asBinder() {
                return mWindowManager != null ? mWindowManager.asBinder() : null;
            }

            @Override
            public int getInitialDisplayDensity(int displayId) throws RemoteException {
                return mWindowManager != null ? mWindowManager.getInitialDisplayDensity(displayId) : 0;
            }

            @Override
            public void getInitialDisplaySize(int displayId, Point outSize) throws RemoteException {
                if (mWindowManager != null) {
                    mWindowManager.getInitialDisplaySize(displayId, outSize);
                }
            }

            @Override
            public int getBaseDisplayDensity(int displayId) throws RemoteException {
                return mWindowManager != null ? mWindowManager.getBaseDisplayDensity(displayId) : 0;
            }

            @Override
            public void getBaseDisplaySize(int displayId, Point outSize) throws RemoteException {
                if (mWindowManager != null) {
                    mWindowManager.getBaseDisplaySize(displayId, outSize);
                }
            }

            @Override
            public boolean hasNavigationBar(int displayId) throws RemoteException {
                return mWindowManager != null && mWindowManager.hasNavigationBar(displayId);
            }
        }
    }
}
