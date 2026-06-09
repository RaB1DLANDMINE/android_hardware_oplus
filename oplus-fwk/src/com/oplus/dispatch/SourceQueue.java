package com.oplus.dispatch;

public class SourceQueue {
    public final String mName;
    public final QueueAttr mAttr;
    private final DispatchQueue mScheduleQueue;

    SourceQueue(String name, QueueAttr attr, DispatchQueue scheduleQueue) {
        mName = name;
        mAttr = attr != null ? attr : QueueAttr.QUEUE_SERIAL;
        mScheduleQueue = scheduleQueue;
    }

    public QueueAttr getQueueAttr() {
        return mAttr;
    }

    public DispatchQueue getScheduleQueue() {
        return mScheduleQueue;
    }

    public DispatchQueue getTargetQueue() {
        return mScheduleQueue;
    }

    public int getWorkSize() {
        return 0;
    }

    public boolean hasDispatchWork(Runnable work) {
        return false;
    }

    public void setSlowLogThresholdMs(long slowDispatchThresholdMs, long slowDeliveryThresholdMs) {
    }

    public void setTraceTag(long traceTag) {
    }
}
