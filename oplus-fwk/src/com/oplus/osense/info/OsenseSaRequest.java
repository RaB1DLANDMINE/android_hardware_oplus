package com.oplus.osense.info;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class OsenseSaRequest implements Parcelable {
    public static final Creator<OsenseSaRequest> CREATOR = new Creator<OsenseSaRequest>() {
        @Override
        public OsenseSaRequest createFromParcel(Parcel in) {
            return new OsenseSaRequest(in);
        }

        @Override
        public OsenseSaRequest[] newArray(int size) {
            return new OsenseSaRequest[size];
        }
    };

    private String mAction = "";
    private Bundle mInfo;
    private String mScene = "";
    private int mTimeout = -1;

    public OsenseSaRequest() {
    }

    public OsenseSaRequest(String scene, String action, int timeout) {
        mScene = scene;
        mAction = action;
        mTimeout = timeout;
    }

    public OsenseSaRequest(Bundle info) {
        mInfo = info;
    }

    protected OsenseSaRequest(Parcel in) {
        readFromParcel(in);
    }

    public String getScene() {
        return mScene;
    }

    public String getAction() {
        return mAction;
    }

    public int getTimeout() {
        return mTimeout;
    }

    public Bundle getInfo() {
        return mInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mScene);
        dest.writeString(mAction);
        dest.writeInt(mTimeout);
        dest.writeBundle(mInfo);
    }

    protected void readFromParcel(Parcel in) {
        mScene = in.readString();
        mAction = in.readString();
        mTimeout = in.readInt();
        mInfo = in.readBundle();
    }

    @Override
    public String toString() {
        return "OsenseSaRequest{scene='" + mScene + "', action='" + mAction
                + "', timeout=" + mTimeout + ", info=" + mInfo + '}';
    }
}
