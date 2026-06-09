package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOplusAudioManager extends IInterface {
    String DESCRIPTOR = "android.media.IOplusAudioManager";

    void setRingerModeInternal(int ringerMode) throws RemoteException;

    class Default implements IOplusAudioManager {
        @Override
        public void setRingerModeInternal(int ringerMode) throws RemoteException {
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    abstract class Stub extends Binder implements IOplusAudioManager {
        static final int TRANSACTION_setRingerModeInternal = IBinder.FIRST_CALL_TRANSACTION;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOplusAudioManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }

            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin instanceof IOplusAudioManager) {
                return (IOplusAudioManager) iin;
            }

            return new Proxy(obj);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            if (transactionCode == TRANSACTION_setRingerModeInternal) {
                return "setRingerModeInternal";
            }
            return null;
        }

        @Override
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags)
                throws RemoteException {
            if (code >= IBinder.FIRST_CALL_TRANSACTION && code <= IBinder.LAST_CALL_TRANSACTION) {
                data.enforceInterface(DESCRIPTOR);
            }

            if (code == INTERFACE_TRANSACTION) {
                reply.writeString(DESCRIPTOR);
                return true;
            }

            if (code == TRANSACTION_setRingerModeInternal) {
                int ringerMode = data.readInt();
                data.enforceNoDataAvail();
                setRingerModeInternal(ringerMode);
                reply.writeNoException();
                return true;
            }

            return super.onTransact(code, data, reply, flags);
        }

        private static class Proxy implements IOplusAudioManager {
            private final IBinder mRemote;

            Proxy(IBinder remote) {
                mRemote = remote;
            }

            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public void setRingerModeInternal(int ringerMode) throws RemoteException {
                Parcel data = Parcel.obtain(asBinder());
                Parcel reply = Parcel.obtain();
                try {
                    data.writeInterfaceToken(DESCRIPTOR);
                    data.writeInt(ringerMode);
                    mRemote.transact(TRANSACTION_setRingerModeInternal, data, reply, 0);
                    reply.readException();
                } finally {
                    reply.recycle();
                    data.recycle();
                }
            }
        }

        public int getMaxTransactionId() {
            return 0;
        }
    }
}
