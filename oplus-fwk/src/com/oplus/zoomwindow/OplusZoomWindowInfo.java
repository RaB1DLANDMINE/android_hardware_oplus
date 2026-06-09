package com.oplus.zoomwindow;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class OplusZoomWindowInfo implements Parcelable {
    public static final Creator<OplusZoomWindowInfo> CREATOR = new Creator<OplusZoomWindowInfo>() {
        @Override
        public OplusZoomWindowInfo createFromParcel(Parcel source) {
            return new OplusZoomWindowInfo(source);
        }

        @Override
        public OplusZoomWindowInfo[] newArray(int size) {
            return new OplusZoomWindowInfo[size];
        }
    };

    public String cpnName;
    public int cvActionFlag;
    public Bundle extension = new Bundle();
    public int inputMethodType;
    public boolean inputShow;
    public int lastExitMethod;
    public float leftScaleOfFloatHandleCenter;
    public String lockPkg;
    public int lockUserId;
    public float rightScaleOfFloatHandleCenter;
    public int rotation;
    public float scale;
    public int sideOfFloatHandle;
    public int systemRotation;
    public boolean windowShown;
    public int windowType;
    public String zoomPkg;
    public Rect zoomRect = new Rect();
    public int zoomUserId;

    public OplusZoomWindowInfo() {
    }

    public OplusZoomWindowInfo(OplusZoomWindowInfo in) {
        if (in != null) {
            rotation = in.rotation;
            systemRotation = in.systemRotation;
            windowShown = in.windowShown;
            lockPkg = in.lockPkg;
            zoomRect = in.zoomRect;
            scale = in.scale;
            zoomPkg = in.zoomPkg;
            lockUserId = in.lockUserId;
            zoomUserId = in.zoomUserId;
            inputShow = in.inputShow;
            cpnName = in.cpnName;
            lastExitMethod = in.lastExitMethod;
            inputMethodType = in.inputMethodType;
            extension = new Bundle(in.extension);
            cvActionFlag = in.cvActionFlag;
            windowType = in.windowType;
            leftScaleOfFloatHandleCenter = in.leftScaleOfFloatHandleCenter;
            rightScaleOfFloatHandleCenter = in.rightScaleOfFloatHandleCenter;
            sideOfFloatHandle = in.sideOfFloatHandle;
        }
    }

    public OplusZoomWindowInfo(Parcel in) {
        rotation = in.readInt();
        systemRotation = in.readInt();
        windowShown = in.readByte() != 0;
        lockPkg = in.readString();
        zoomRect = in.readParcelable(null);
        scale = in.readFloat();
        zoomPkg = in.readString();
        lockUserId = in.readInt();
        zoomUserId = in.readInt();
        inputShow = in.readByte() != 0;
        cpnName = in.readString();
        lastExitMethod = in.readInt();
        inputMethodType = in.readInt();
        extension = in.readBundle();
        cvActionFlag = in.readInt();
        windowType = in.readInt();
        leftScaleOfFloatHandleCenter = in.readFloat();
        rightScaleOfFloatHandleCenter = in.readFloat();
        sideOfFloatHandle = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(rotation);
        parcel.writeInt(systemRotation);
        parcel.writeByte(windowShown ? (byte) 1 : (byte) 0);
        parcel.writeString(lockPkg);
        parcel.writeParcelable(zoomRect, 0);
        parcel.writeFloat(scale);
        parcel.writeString(zoomPkg);
        parcel.writeInt(lockUserId);
        parcel.writeInt(zoomUserId);
        parcel.writeByte(inputShow ? (byte) 1 : (byte) 0);
        parcel.writeString(cpnName);
        parcel.writeInt(lastExitMethod);
        parcel.writeInt(inputMethodType);
        parcel.writeBundle(extension);
        parcel.writeInt(cvActionFlag);
        parcel.writeInt(windowType);
        parcel.writeFloat(leftScaleOfFloatHandleCenter);
        parcel.writeFloat(rightScaleOfFloatHandleCenter);
        parcel.writeInt(sideOfFloatHandle);
    }
}
