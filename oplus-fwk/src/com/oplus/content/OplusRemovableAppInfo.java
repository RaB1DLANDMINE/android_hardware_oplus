package com.oplus.content;

import android.os.Parcel;
import android.os.Parcelable;

public class OplusRemovableAppInfo implements Parcelable {
    public static final Creator<OplusRemovableAppInfo> CREATOR = new Creator<OplusRemovableAppInfo>() {
        @Override
        public OplusRemovableAppInfo createFromParcel(Parcel in) {
            return new OplusRemovableAppInfo(in);
        }

        @Override
        public OplusRemovableAppInfo[] newArray(int size) {
            return new OplusRemovableAppInfo[size];
        }
    };

    private String mPackageName = "";

    public OplusRemovableAppInfo(String packageName) {
        mPackageName = packageName;
    }

    public OplusRemovableAppInfo(Parcel in) {
        mPackageName = in.readString();
    }

    public String getPackageName() {
        return mPackageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPackageName);
    }
}
