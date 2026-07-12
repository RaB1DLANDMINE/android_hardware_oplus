package com.oplus.dispatch;

import android.os.HandlerThread;
import android.os.Looper;

public class OCDMessageQueue extends SourceQueue {
    private static final HandlerThread DEFAULT_THREAD = createThread();

    OCDMessageQueue(String name, QueueAttr attr, DispatchQueue scheduleQueue) {
        super(name, attr, scheduleQueue);
    }

    private static HandlerThread createThread() {
        HandlerThread thread = new HandlerThread("oplus-ocd");
        thread.start();
        return thread;
    }

    public static OCDMessageQueue getCurrentThreadOCDMessageQueue() {
        return new OCDMessageQueue("current", QueueAttr.QUEUE_SERIAL, new DispatchQueue());
    }

    Looper getLooper() {
        Looper looper = Looper.myLooper();
        return looper != null ? looper : DEFAULT_THREAD.getLooper();
    }
}
