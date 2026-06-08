package com.oplus.file.extender;

public class OplusFileExtender {
    private String mTag;

    public OplusFileExtender(int fd) {
    }

    public OplusFileExtender(String path) {
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        this.mTag = tag;
    }

    public void setExtensionData(String key, String value) {
    }

    public void setExtensionData(String key, byte[] value) {
    }

    public String getExtensionDataString(String key) {
        return null;
    }

    public byte[] getExtensionDataByteArray(String key) {
        return null;
    }

    public void close() {
    }
}
