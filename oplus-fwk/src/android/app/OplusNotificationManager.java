/*
 * Copyright (C) 2026 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package android.app;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Singleton;

public class OplusNotificationManager {
    private static final Singleton<IOplusNotificationManager> MANAGER_SINGLETON =
            new Singleton<IOplusNotificationManager>() {
                @Override
                protected IOplusNotificationManager create() {
                    try {
                        IBinder extension = NotificationManager.getService().asBinder().getExtension();
                        return IOplusNotificationManager.Stub.asInterface(extension);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            };

    public static IOplusNotificationManager getService() {
        return MANAGER_SINGLETON.get();
    }

    public String getOpenid(String pkg, int uid, String type) {
        return getStdid(pkg, uid, type);
    }

    public void clearOpenid(String pkg, int uid, String type) {
        clearStdid(pkg, uid, type);
    }

    public boolean checkGetOpenid(String pkg, int uid, String type) {
        return checkGetStdid(pkg, uid, type);
    }

    public String getStdid(String pkg, int uid, String type) {
        try {
            return getService().getStdid(pkg, uid, type);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void clearStdid(String pkg, int uid, String type) {
        try {
            getService().clearStdid(pkg, uid, type);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean checkGetStdid(String pkg, int uid, String type) {
        try {
            return getService().checkGetStdid(pkg, uid, type);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
