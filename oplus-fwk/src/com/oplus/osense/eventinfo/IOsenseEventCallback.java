package com.oplus.osense.eventinfo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public interface IOsenseEventCallback extends IInterface {
    class Default implements IOsenseEventCallback {
        @Override
        public IBinder asBinder() {
            return null;
        }
    }

    abstract class Stub extends Binder implements IOsenseEventCallback {
        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
