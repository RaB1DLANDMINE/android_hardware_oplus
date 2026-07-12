package com.oplus.dispatch;

public class QueueAttr {
    public static final QueueAttr QUEUE_SERIAL = new QueueAttr(false, false);
    public static final QueueAttr QUEUE_CONCURRENT = new QueueAttr(true, false);
    public static final QueueAttr QUEUE_SERIAL_TIME = new QueueAttr(false, true);
    public static final QueueAttr QUEUE_FG_TIME = new QueueAttr(false, true);
    public static final QueueAttr QUEUE_BG_TIME = new QueueAttr(false, true);

    public final boolean mConcurrent;
    public final boolean mIsTimeQueue;

    public QueueAttr() {
        this(false, false);
    }

    private QueueAttr(boolean concurrent, boolean timeQueue) {
        mConcurrent = concurrent;
        mIsTimeQueue = timeQueue;
    }

    public static class Builder {
        private boolean mConcurrent;
        private boolean mTimeQueue;

        public Builder() {
        }

        public Builder setConcurrent(boolean concurrent) {
            mConcurrent = concurrent;
            return this;
        }

        public Builder setTimeQueue(boolean timeQueue) {
            mTimeQueue = timeQueue;
            return this;
        }

        public QueueAttr build() {
            return new QueueAttr(mConcurrent, mTimeQueue);
        }
    }
}
