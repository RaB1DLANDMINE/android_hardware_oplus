package com.oplus.wrapper.os;

import android.os.IBinder;

public class ServiceManager {
    public static IBinder checkService(String name) {
        return android.os.ServiceManager.checkService(name);
    }

    public static IBinder getService(String name) {
        return android.os.ServiceManager.getService(name);
    }

    public static void addService(String name, IBinder service) {
        android.os.ServiceManager.addService(name, service);
    }

    public static boolean isDeclared(String name) {
        return android.os.ServiceManager.isDeclared(name);
    }

    public static IBinder waitForService(String name) {
        return android.os.ServiceManager.waitForService(name);
    }
}
