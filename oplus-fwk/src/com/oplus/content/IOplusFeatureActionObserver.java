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

public interface IOplusFeatureActionObserver extends IInterface {
    String DESCRIPTOR = "com.oplus.content.IOplusFeatureActionObserver";

    void onFeaturesActionUpdate(String action, String actionValue, int featureID) throws RemoteException;

    class Default implements IOplusFeatureActionObserver {
        @Override
        public void onFeaturesActionUpdate(String action, String actionValue, int featureID) throws RemoteException {
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    abstract class Stub extends Binder implements IOplusFeatureActionObserver {
        static final int TRANSACTION_onFeaturesActionUpdate = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOplusFeatureActionObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOplusFeatureActionObserver) {
                return (IOplusFeatureActionObserver) iin;
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            return transactionCode == TRANSACTION_onFeaturesActionUpdate ? "onFeaturesActionUpdate" : null;
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
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
            if (code == TRANSACTION_onFeaturesActionUpdate) {
                String action = data.readString();
                String actionValue = data.readString();
                int featureID = data.readInt();
                data.enforceNoDataAvail();
                onFeaturesActionUpdate(action, actionValue, featureID);
                return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IOplusFeatureActionObserver {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void onFeaturesActionUpdate(String action, String actionValue, int featureID) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeString(action);
                    data.writeString(actionValue);
                    data.writeInt(featureID);
                    mRemote.transact(TRANSACTION_onFeaturesActionUpdate, data, null, IBinder.FLAG_ONEWAY);
                } finally {
                    data.recycle();
                }
            }
        }

        public int getMaxTransactionId() {
            return 0;
        }
    }
}
