/*
 * Copyright (C) 2026 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOplusNotificationManager extends IInterface {
    String DESCRIPTOR = "android.app.IOplusNotificationManager";

    String getStdid(String pkg, int uid, String type) throws RemoteException;

    void clearStdid(String pkg, int uid, String type) throws RemoteException;

    boolean checkGetStdid(String pkg, int uid, String type) throws RemoteException;

    abstract class Stub extends Binder implements IOplusNotificationManager {
        static final int TRANSACTION_getStdid = 9;
        static final int TRANSACTION_clearStdid = 10;
        static final int TRANSACTION_checkGetStdid = 17;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOplusNotificationManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }

            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOplusNotificationManager) {
                return (IOplusNotificationManager) iin;
            }

            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        private static class Proxy implements IOplusNotificationManager {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public String getStdid(String pkg, int uid, String type) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeString(pkg);
                    data.writeInt(uid);
                    data.writeString(type);
                    mRemote.transact(TRANSACTION_getStdid, data, reply, 0);
                    reply.readException();
                    return reply.readString();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            @Override
            public void clearStdid(String pkg, int uid, String type) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeString(pkg);
                    data.writeInt(uid);
                    data.writeString(type);
                    mRemote.transact(TRANSACTION_clearStdid, data, reply, 0);
                    reply.readException();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }

            @Override
            public boolean checkGetStdid(String pkg, int uid, String type) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeString(pkg);
                    data.writeInt(uid);
                    data.writeString(type);
                    mRemote.transact(TRANSACTION_checkGetStdid, data, reply, 0);
                    reply.readException();
                    return reply.readBoolean();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }
        }
    }
}
