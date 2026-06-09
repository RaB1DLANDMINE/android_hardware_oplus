package com.oplus.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOplusAccessControlObserver extends IInterface {
    String DESCRIPTOR = "com.oplus.app.IOplusAccessControlObserver";

    void onEncryptStateChange(OplusAccessControlInfo info) throws RemoteException;

    void onHideStateChange(OplusAccessControlInfo info) throws RemoteException;

    void onEncryptEnableChange(boolean enable) throws RemoteException;

    void onHideEnableChange(boolean enable) throws RemoteException;

    class Default implements IOplusAccessControlObserver {
        @Override
        public void onEncryptStateChange(OplusAccessControlInfo info) throws RemoteException {
        }

        @Override
        public void onHideStateChange(OplusAccessControlInfo info) throws RemoteException {
        }

        @Override
        public void onEncryptEnableChange(boolean enable) throws RemoteException {
        }

        @Override
        public void onHideEnableChange(boolean enable) throws RemoteException {
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    abstract class Stub extends Binder implements IOplusAccessControlObserver {
        static final int TRANSACTION_onEncryptStateChange = IBinder.FIRST_CALL_TRANSACTION;
        static final int TRANSACTION_onHideStateChange = IBinder.FIRST_CALL_TRANSACTION + 1;
        static final int TRANSACTION_onEncryptEnableChange = IBinder.FIRST_CALL_TRANSACTION + 2;
        static final int TRANSACTION_onHideEnableChange = IBinder.FIRST_CALL_TRANSACTION + 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOplusAccessControlObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOplusAccessControlObserver) {
                return (IOplusAccessControlObserver) iin;
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= IBinder.FIRST_CALL_TRANSACTION && code <= 0x00ffffff) {
                data.enforceInterface(DESCRIPTOR);
            }
            if (code == INTERFACE_TRANSACTION) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case TRANSACTION_onEncryptStateChange:
                    onEncryptStateChange(data.readTypedObject(OplusAccessControlInfo.CREATOR));
                    return true;
                case TRANSACTION_onHideStateChange:
                    onHideStateChange(data.readTypedObject(OplusAccessControlInfo.CREATOR));
                    return true;
                case TRANSACTION_onEncryptEnableChange:
                    onEncryptEnableChange(data.readBoolean());
                    return true;
                case TRANSACTION_onHideEnableChange:
                    onHideEnableChange(data.readBoolean());
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IOplusAccessControlObserver {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void onEncryptStateChange(OplusAccessControlInfo info) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeTypedObject(info, 0);
                    mRemote.transact(TRANSACTION_onEncryptStateChange, data, null, IBinder.FLAG_ONEWAY);
                } finally {
                    data.recycle();
                }
            }

            @Override
            public void onHideStateChange(OplusAccessControlInfo info) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeTypedObject(info, 0);
                    mRemote.transact(TRANSACTION_onHideStateChange, data, null, IBinder.FLAG_ONEWAY);
                } finally {
                    data.recycle();
                }
            }

            @Override
            public void onEncryptEnableChange(boolean enable) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeBoolean(enable);
                    mRemote.transact(TRANSACTION_onEncryptEnableChange, data, null, IBinder.FLAG_ONEWAY);
                } finally {
                    data.recycle();
                }
            }

            @Override
            public void onHideEnableChange(boolean enable) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeBoolean(enable);
                    mRemote.transact(TRANSACTION_onHideEnableChange, data, null, IBinder.FLAG_ONEWAY);
                } finally {
                    data.recycle();
                }
            }
        }
    }
}
