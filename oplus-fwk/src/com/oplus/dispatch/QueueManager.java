package com.oplus.dispatch;

public class QueueManager {
    public static SourceQueue queueCreate(String name, QueueAttr attr) {
        return new SourceQueue(name, attr, new DispatchQueue());
    }

    public static SourceQueue queueCreate(String name, QueueAttr attr, DispatchQueue dispatchQueue) {
        return new SourceQueue(name, attr, dispatchQueue);
    }

    public static OCDMessageQueue messageQueueCreate(String name, QueueAttr attr, DispatchQueue dispatchQueue) {
        return new OCDMessageQueue(name, attr, dispatchQueue != null ? dispatchQueue : new DispatchQueue());
    }
}
