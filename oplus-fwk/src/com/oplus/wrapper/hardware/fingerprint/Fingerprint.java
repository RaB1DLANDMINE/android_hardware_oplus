package com.oplus.wrapper.hardware.fingerprint;

import android.util.Log;
import java.lang.reflect.Method;

public class Fingerprint {
    private static final String TAG = "FingerprintWrapper";
    private final Object mFingerprint;

    public Fingerprint(android.hardware.fingerprint.Fingerprint fingerprint) {
        this((Object) fingerprint);
    }

    public Fingerprint(Object fingerprint) {
        mFingerprint = fingerprint;
    }

    public int getBiometricId() {
        Object result = call("getBiometricId", false);
        if (!(result instanceof Number)) {
            result = call("getFingerId", true);
        }
        return result instanceof Number ? ((Number) result).intValue() : 0;
    }

    public int getFingerId() {
        return callInt("getFingerId");
    }

    public int getGroupId() {
        return callInt("getGroupId");
    }

    public CharSequence getName() {
        Object result = call("getName");
        return result instanceof CharSequence ? (CharSequence) result : "";
    }

    public long getDeviceId() {
        Object result = call("getDeviceId");
        return result instanceof Number ? ((Number) result).longValue() : 0L;
    }

    private int callInt(String methodName) {
        Object result = call(methodName, true);
        return result instanceof Number ? ((Number) result).intValue() : 0;
    }

    private Object call(String methodName) {
        return call(methodName, true);
    }

    private Object call(String methodName, boolean logFailure) {
        if (mFingerprint == null) {
            return null;
        }

        try {
            Method method = findMethod(mFingerprint.getClass(), methodName);
            method.setAccessible(true);
            return method.invoke(mFingerprint);
        } catch (ReflectiveOperationException | RuntimeException e) {
            if (logFailure) {
                Log.e(TAG, "Failed to call " + methodName, e);
            }
            return null;
        }
    }

    private static Method findMethod(Class<?> klass, String methodName) throws NoSuchMethodException {
        Class<?> current = klass;
        while (current != null) {
            try {
                return current.getDeclaredMethod(methodName);
            } catch (NoSuchMethodException ignored) {
                current = current.getSuperclass();
            }
        }
        throw new NoSuchMethodException(klass.getName() + "." + methodName + " []");
    }
}
