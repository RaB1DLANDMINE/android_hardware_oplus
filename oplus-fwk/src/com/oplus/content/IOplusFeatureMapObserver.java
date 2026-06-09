/*
 * Copyright (C) 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.content;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IOplusFeatureMapObserver extends IInterface {
    String DESCRIPTOR = "com.oplus.content.IOplusFeatureMapObserver";

    void onFeatureUpdate(List<String> features, int featureID) throws RemoteException;

    class Default implements IOplusFeatureMapObserver {
        @Override
        public void onFeatureUpdate(List<String> features, int featureID) throws RemoteException {
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    abstract class Stub extends Binder implements IOplusFeatureMapObserver {
        static final int TRANSACTION_onFeatureUpdate = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOplusFeatureMapObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOplusFeatureMapObserver) {
                return (IOplusFeatureMapObserver) iin;
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= IBinder.FIRST_CALL_TRANSACTION && code <= 0x00ffffff) {
                data.enforceInterface(DESCRIPTOR);
            }
            if (code == INTERFACE_TRANSACTION) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            if (code == TRANSACTION_onFeatureUpdate) {
                List<String> features = data.createStringArrayList();
                int featureID = data.readInt();
                data.enforceNoDataAvail();
                onFeatureUpdate(features, featureID);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IOplusFeatureMapObserver {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void onFeatureUpdate(List<String> features, int featureID) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeStringList(features);
                    data.writeInt(featureID);
                    mRemote.transact(TRANSACTION_onFeatureUpdate, data, null, IBinder.FLAG_ONEWAY);
                } finally {
                    data.recycle();
                }
            }
        }
    }
}
