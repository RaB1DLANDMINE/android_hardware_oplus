package com.oplus.ota;

import android.os.Parcel;
import android.os.Parcelable;

public class OplusSystemUpdateInfo implements Parcelable {
    public static final int TYPE_NO_UPDATE = 0;
    public static final int TYPE_OTA_UPDATE = 1;
    public static final int TYPE_RECOVERY_UPDATE = 3;
    public static final int TYPE_SAU_UPDATE = 2;

    public static final Creator<OplusSystemUpdateInfo> CREATOR = new Creator<OplusSystemUpdateInfo>() {
        @Override
        public OplusSystemUpdateInfo createFromParcel(Parcel in) {
            return new OplusSystemUpdateInfo(in);
        }

        @Override
        public OplusSystemUpdateInfo[] newArray(int size) {
            return new OplusSystemUpdateInfo[size];
        }
    };

    private boolean mUpdated;
    private int mUpdateType;
    private boolean mUpdateSucc;
    private int mFailedType;
    private String mFailedMsg = "";

    public OplusSystemUpdateInfo() {
    }

    public OplusSystemUpdateInfo(int updateType, boolean updateSucc, int failedType, String failedMsg) {
        mUpdated = true;
        mUpdateType = updateType;
        mUpdateSucc = updateSucc;
        mFailedType = failedType;
        mFailedMsg = failedMsg;
    }

    public OplusSystemUpdateInfo(Parcel in) {
        mUpdated = in.readBoolean();
        mUpdateType = in.readInt();
        mUpdateSucc = in.readBoolean();
        mFailedType = in.readInt();
        mFailedMsg = in.readString();
    }

    public boolean isUpdated() {
        return mUpdated;
    }

    public int getUpdateType() {
        return mUpdateType;
    }

    public boolean isUpdateSucc() {
        return mUpdateSucc;
    }

    public int getFailedType() {
        return mFailedType;
    }

    public String getFailedMsg() {
        return mFailedMsg;
    }

    public void setUpdateType(int updateType) {
        mUpdated = true;
        mUpdateType = updateType;
    }

    public void setUpdateSucc(boolean updateSucc) {
        mUpdated = true;
        mUpdateSucc = updateSucc;
    }

    public void setFailedType(int failedType) {
        mFailedType = failedType;
    }

    public void setFailedMsg(String failedMsg) {
        mFailedMsg = failedMsg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBoolean(mUpdated);
        dest.writeInt(mUpdateType);
        dest.writeBoolean(mUpdateSucc);
        dest.writeInt(mFailedType);
        dest.writeString(mFailedMsg);
    }
}
