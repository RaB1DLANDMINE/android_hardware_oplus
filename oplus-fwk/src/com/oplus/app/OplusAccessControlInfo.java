package com.oplus.app;

import android.os.Parcel;
import android.os.Parcelable;

public final class OplusAccessControlInfo implements Parcelable {
    public static final Parcelable.Creator<OplusAccessControlInfo> CREATOR =
            new Parcelable.Creator<OplusAccessControlInfo>() {
                @Override
                public OplusAccessControlInfo createFromParcel(Parcel source) {
                    return new OplusAccessControlInfo(source);
                }

                @Override
                public OplusAccessControlInfo[] newArray(int size) {
                    return new OplusAccessControlInfo[size];
                }
            };

    public boolean isEncrypted;
    public boolean isHideIcon;
    public boolean isHideInRecent;
    public boolean isHideNotice;
    public String mName;
    public int userId;

    public OplusAccessControlInfo() {
    }

    public OplusAccessControlInfo(Parcel in) {
        mName = in.readString();
        userId = in.readInt();
        isEncrypted = in.readByte() != 0;
        isHideIcon = in.readByte() != 0;
        isHideInRecent = in.readByte() != 0;
        isHideNotice = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(userId);
        dest.writeByte(isEncrypted ? (byte) 1 : (byte) 0);
        dest.writeByte(isHideIcon ? (byte) 1 : (byte) 0);
        dest.writeByte(isHideInRecent ? (byte) 1 : (byte) 0);
        dest.writeByte(isHideNotice ? (byte) 1 : (byte) 0);
    }

    @Override
    public String toString() {
        return "OplusAccessControlInfo = { "
                + " mName = " + mName
                + " userId = " + userId
                + " isEncrypted = " + isEncrypted
                + " isHideIcon = " + isHideIcon
                + " isHideInRecent = " + isHideInRecent
                + " isHideNotice = " + isHideNotice
                + "}";
    }
}
