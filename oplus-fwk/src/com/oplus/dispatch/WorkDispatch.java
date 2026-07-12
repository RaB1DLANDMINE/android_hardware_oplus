package com.oplus.dispatch;

import android.os.Handler;
import android.os.Looper;

public class WorkDispatch {
    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    private WorkDispatch() {
    }

    public static void init() {
    }

    public static void init(IOCDApi api) {
    }

    public static boolean isOCDEnable() {
        return false;
    }

    public static boolean isOcdApp() {
        return false;
    }

    public static int getVersionCode() {
        return 1;
    }

    public static int getCurrentQos() {
        return -1;
    }

    public static int dispatchAsync(SourceQueue sourceQueue, Runnable work) {
        return dispatchAsync(sourceQueue, work, null, -1);
    }

    public static int dispatchAsync(SourceQueue sourceQueue, Runnable work, String name) {
        return dispatchAsync(sourceQueue, work, name, -1);
    }

    public static int dispatchAsync(SourceQueue sourceQueue, Runnable work, String name, int qos) {
        if (work != null) {
            new Thread(work, name != null ? name : "oplus-dispatch").start();
        }
        return 0;
    }

    public static int dispatchAsync(SourceQueue sourceQueue, Runnable work, String name, int qos, long delayMillis) {
        dispatchAfter(sourceQueue, work, name, delayMillis, qos);
        return 0;
    }

    public static void dispatchAfter(SourceQueue sourceQueue, Runnable work, long uptimeMillis) {
        dispatchAfter(sourceQueue, work, null, uptimeMillis, -1);
    }

    public static void dispatchAfter(SourceQueue sourceQueue, Runnable work, String name, long uptimeMillis) {
        dispatchAfter(sourceQueue, work, name, uptimeMillis, -1);
    }

    public static void dispatchAfter(SourceQueue sourceQueue, Runnable work, String name, long uptimeMillis, int qos) {
        if (work != null) {
            MAIN.postDelayed(work, Math.max(0L, uptimeMillis));
        }
    }

    public static int dispatchSync(SourceQueue sourceQueue, Runnable work) {
        return dispatchSync(sourceQueue, work, null, -1);
    }

    public static int dispatchSync(SourceQueue sourceQueue, Runnable work, String name) {
        return dispatchSync(sourceQueue, work, name, -1);
    }

    public static int dispatchSync(SourceQueue sourceQueue, Runnable work, String name, int qos) {
        if (work != null) {
            work.run();
        }
        return 0;
    }

    public static int dispatchAsyncAndWait(SourceQueue sourceQueue, Runnable work) {
        return dispatchSync(sourceQueue, work);
    }

    public static int dispatchAsyncAndWait(SourceQueue sourceQueue, Runnable work, String name) {
        return dispatchSync(sourceQueue, work, name);
    }

    public static int dispatchAsyncAndWait(SourceQueue sourceQueue, Runnable work, String name, int qos) {
        return dispatchSync(sourceQueue, work, name, qos);
    }

    public static OTask dispatchAsyncDeps(SourceQueue sourceQueue, Runnable work) {
        OTask task = new OTask(work);
        dispatchAsync(sourceQueue, task);
        return task;
    }

    public static OTask dispatchAsyncDeps(SourceQueue sourceQueue, Runnable work, OTaskAttr attr) {
        return dispatchAsyncDeps(sourceQueue, work);
    }

    public static int dispatchAsyncMain(Runnable work, boolean front) {
        if (work != null) {
            MAIN.post(work);
        }
        return 0;
    }

    public static void depsWait() throws InterruptedException {
    }

    public static void depsWait(OTask... deps) throws InterruptedException {
    }
}
