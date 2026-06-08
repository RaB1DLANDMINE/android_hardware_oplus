package com.oplus.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOplusSplitScreenObserver extends IInterface {
    public static final String DESCRIPTOR = "com.oplus.app.IOplusSplitScreenObserver";

    void onStateChanged(String event, Bundle bundle) throws RemoteException;

    public static class Default implements IOplusSplitScreenObserver {
        @Override
        public void onStateChanged(String event, Bundle bundle) throws RemoteException {
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOplusSplitScreenObserver {
        static final int TRANSACTION_onStateChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOplusSplitScreenObserver asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IOplusSplitScreenObserver)) {
                return (IOplusSplitScreenObserver) iin;
            }
            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case TRANSACTION_onStateChanged:
                    return "onStateChanged";
                default:
                    return null;
            }
        }

        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code >= 1 && code <= 16777215) {
                data.enforceInterface(DESCRIPTOR);
            }
            switch (code) {
                case TRANSACTION_onStateChanged:
                    String _arg0 = data.readString();
                    Bundle _arg1 = (Bundle) data.readTypedObject(Bundle.CREATOR);
                    data.enforceNoDataAvail();
                    onStateChanged(_arg0, _arg1);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static final class Proxy implements IOplusSplitScreenObserver {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return this.mRemote;
            }

            public final String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public void onStateChanged(String event, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain(asBinder());
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(event);
                    _data.writeTypedObject(bundle, 0);
                    this.mRemote.transact(TRANSACTION_onStateChanged, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public int getMaxTransactionId() {
            return 0;
        }
    }
}
