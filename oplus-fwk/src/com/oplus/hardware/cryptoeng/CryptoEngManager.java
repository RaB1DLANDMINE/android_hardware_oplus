package com.oplus.hardware.cryptoeng;

public class CryptoEngManager {
    private static volatile CryptoEngManager sInstance;

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

    public byte[] cryptoEngCommand(byte[] inData) {
        return null;
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
