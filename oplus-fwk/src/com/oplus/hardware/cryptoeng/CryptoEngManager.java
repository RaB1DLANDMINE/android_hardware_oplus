package com.oplus.hardware.cryptoeng;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

public class CryptoEngManager {
    private static final String TAG = "CryptoEngManager";
    private static final String DESCRIPTOR = "vendor.oplus.hardware.cryptoeng.ICryptoeng";
    private static final int TRANSACTION_CRYPTOENG_INVOKE_COMMAND = IBinder.FIRST_CALL_TRANSACTION;
    private static volatile CryptoEngManager sInstance;
    private static final String SERVICE_NAME = DESCRIPTOR + "/default";

    private volatile IBinder mCryptoEngService;
    private final IBinder.DeathRecipient mDeathRecipient =
            new IBinder.DeathRecipient() {
                @Override
                public void binderDied() {
                    Log.i(TAG, SERVICE_NAME + " binderDied");
                    synchronized (CryptoEngManager.class) {
                        mCryptoEngService = null;
                    }
                }
            };

    private CryptoEngManager() {
    }

    public static CryptoEngManager getInstance() {
        if (sInstance == null) {
            synchronized (CryptoEngManager.class) {
                if (sInstance == null) {
                    sInstance = new CryptoEngManager();
                }
            }
        }
        return sInstance;
    }

    private synchronized IBinder getService() {
        if (mCryptoEngService == null) {
            IBinder binder = ServiceManager.getService(SERVICE_NAME);
            if (binder == null) {
                Log.w(TAG, "getService fail. " + SERVICE_NAME);
                return null;
            }
            try {
                binder.linkToDeath(mDeathRecipient, 0);
                mCryptoEngService = binder;
            } catch (RemoteException e) {
                Log.e(TAG, "linkToDeath fail", e);
                return null;
            }
        }
        return mCryptoEngService;
    }

    public byte[] cryptoEngCommand(byte[] inData) {
        IBinder service = getService();
        if (service == null) {
            return null;
        }

        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeByteArray(inData);
            service.transact(TRANSACTION_CRYPTOENG_INVOKE_COMMAND, data, reply, 0);
            reply.readException();
            return reply.createByteArray();
        } catch (RemoteException e) {
            Log.e(TAG, "cryptoEngCommand failed", e);
            return null;
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    public static class CommandId {
        public static final byte CE_CMD_GOOGLE_ATTESTATION_WRITE = 0x03;
        public static final byte CE_CMD_GOOGLE_ATTESTATION_VERIFY = 0x04;
        public static final byte CE_CMD_FINDPHONE_GET_STATUS = 0x12;
        public static final byte CE_CMD_GENERATE_PKI_CERT = 0x18;
        public static final byte CE_CMD_VERIFY_PKI_CERT = 0x19;
        public static final byte CE_CMD_HDCP_KEY_WRITE = 0x33;
        public static final byte CE_CMD_HDCP_KEY_VERIFY = 0x34;
        public static final byte CE_CMD_CLEAN_UP = 0x35;
        public static final byte CE_CMD_GET_SECURETYPE = 0x36;
        public static final byte CE_CMD_WIDEVINE_SUPPORT = 0x3b;
        public static final byte CE_CMD_CRYPTO_SUPPORT = 0x3c;
        public static final byte CE_CMD_ENGINEER = 0x5a;

        public CommandId() {
        }
    }
}
