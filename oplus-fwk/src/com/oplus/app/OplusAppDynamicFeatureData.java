package com.oplus.app;

import android.os.Parcel;
import android.os.Parcelable;

public class OplusAppDynamicFeatureData implements Parcelable {
    public static final Creator<OplusAppDynamicFeatureData> CREATOR =
            new Creator<OplusAppDynamicFeatureData>() {
                @Override
                public OplusAppDynamicFeatureData createFromParcel(Parcel in) {
                    return new OplusAppDynamicFeatureData(in);
                }

                @Override
                public OplusAppDynamicFeatureData[] newArray(int size) {
                    return new OplusAppDynamicFeatureData[size];
                }
            };

    public OplusAppDynamicFeatureData() {
    }

    public OplusAppDynamicFeatureData(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
