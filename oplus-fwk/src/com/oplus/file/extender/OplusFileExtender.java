package com.oplus.file.extender;

import android.util.Log;

public class OplusFileExtender {
    private static final String TAG = "OplusFileExtender";
    private static final boolean sNativeLoaded;

    static {
        boolean loaded = false;
        try {
            System.loadLibrary("FileExtender-jni");
            loaded = true;
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "Failed to load FileExtender-jni", e);
        }
        sNativeLoaded = loaded;
    }

    private long mNativePtr;
    private String mTag;

    public OplusFileExtender(int fd) {
        if (sNativeLoaded) {
            mNativePtr = openFd(fd);
        }
    }

    public OplusFileExtender(String path) {
        if (sNativeLoaded && path != null) {
            mNativePtr = openPath(path);
        }
    }

    public String getTag() {
        if (mNativePtr != 0) {
            String tag = getTag(mNativePtr);
            if (tag != null) {
                mTag = tag;
            }
        }
        return mTag;
    }

    public void setTag(String tag) {
        this.mTag = tag;
        if (mNativePtr != 0 && tag != null) {
            setTag(mNativePtr, tag);
        }
    }

    public void setExtensionData(String key, String value) {
        if (mNativePtr != 0 && key != null && value != null) {
            setValue(mNativePtr, key, value);
        }
    }

    public void setExtensionData(String key, byte[] value) {
        if (mNativePtr != 0 && key != null && value != null) {
            setBuffer(mNativePtr, key, value);
        }
    }

    public String getExtensionDataString(String key) {
        if (mNativePtr != 0 && key != null) {
            return getValue(mNativePtr, key);
        }
        return null;
    }

    public byte[] getExtensionDataByteArray(String key) {
        if (mNativePtr != 0 && key != null) {
            return getBuffer(mNativePtr, key);
        }
        return null;
    }

    public void setOriginalBuffer(byte[] value) {
        if (mNativePtr != 0 && value != null) {
            setOriginalBuffer(mNativePtr, value);
        }
    }

    public byte[] getOriginalBuffer() {
        if (mNativePtr != 0) {
            return getOriginalBuffer(mNativePtr);
        }
        return null;
    }

    public void close() {
        if (mNativePtr != 0) {
            close(mNativePtr);
            mNativePtr = 0;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    private static native long openPath(String path);

    private static native long openFd(int fd);

    private static native void setTag(long ptr, String tag);

    private static native void setValue(long ptr, String key, String value);

    private static native void setBuffer(long ptr, String key, byte[] value);

    private static native String getTag(long ptr);

    private static native String getValue(long ptr, String key);

    private static native byte[] getBuffer(long ptr, String key);

    private static native void setOriginalBuffer(long ptr, byte[] value);

    private static native byte[] getOriginalBuffer(long ptr);

    private static native void close(long ptr);
}
