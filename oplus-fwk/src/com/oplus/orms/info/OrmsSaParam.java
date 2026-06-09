package com.oplus.orms.info;

import android.os.Parcel;
import android.os.Parcelable;

public class OrmsSaParam implements Parcelable {
    public static final Parcelable.Creator<OrmsSaParam> CREATOR =
            new Parcelable.Creator<OrmsSaParam>() {
                @Override
                public OrmsSaParam createFromParcel(Parcel in) {
                    return new OrmsSaParam(in);
                }

                @Override
                public OrmsSaParam[] newArray(int size) {
                    return new OrmsSaParam[size];
                }
            };

    public String action;
    public String scene;
    public int timeout;

    public OrmsSaParam() {
        scene = "";
        action = "";
        timeout = -1;
    }

    public OrmsSaParam(String scene, String action, int timeout) {
        this.scene = scene;
        this.action = action;
        this.timeout = timeout;
    }

    protected OrmsSaParam(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(scene);
        dest.writeString(action);
        dest.writeInt(timeout);
    }

    public void readFromParcel(Parcel in) {
        scene = in.readString();
        action = in.readString();
        timeout = in.readInt();
    }

    @Override
    public String toString() {
        return "OrmsSaParam{scene='" + scene + "', action='" + action + "', timeout=" + timeout + '}';
    }
}
