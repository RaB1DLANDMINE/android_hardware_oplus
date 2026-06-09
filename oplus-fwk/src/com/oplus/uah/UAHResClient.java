package com.oplus.uah;

import com.oplus.uah.info.UAHEventRequest;

public class UAHResClient {
    public static UAHResClient get(Class<?> cls) {
        return new UAHResClient();
    }

    public int acquireEvent(UAHEventRequest request) {
        return 0;
    }

    public void release(int handle) {
    }
}
