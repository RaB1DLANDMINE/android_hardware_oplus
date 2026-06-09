package com.oplus.dispatch;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class OCDHandler extends Handler {
    private static final HandlerThread DEFAULT_THREAD = createThread();
    private final OCDMessageQueue mQueue;

    public OCDHandler() {
        this(DEFAULT_THREAD.getLooper());
    }

    public OCDHandler(Looper looper) {
        super(looper != null ? looper : DEFAULT_THREAD.getLooper());
        mQueue = new OCDMessageQueue("handler", QueueAttr.QUEUE_SERIAL, new DispatchQueue());
    }

    public OCDHandler(String name) {
        this(DEFAULT_THREAD.getLooper());
    }

    public OCDHandler(String name, Callback callback) {
        super(DEFAULT_THREAD.getLooper(), callback);
        mQueue = new OCDMessageQueue(name, QueueAttr.QUEUE_SERIAL, new DispatchQueue());
    }

    public OCDHandler(OCDMessageQueue queue) {
        super(queue != null ? queue.getLooper() : DEFAULT_THREAD.getLooper());
        mQueue = queue != null ? queue : new OCDMessageQueue("handler", QueueAttr.QUEUE_SERIAL, new DispatchQueue());
    }

    private static HandlerThread createThread() {
        HandlerThread thread = new HandlerThread("oplus-ocd-handler");
        thread.start();
        return thread;
    }

    public OCDMessageQueue getOcdMessageQueue() {
        return mQueue;
    }
}
