package com.oplus.dispatch;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class OCDExecutorService extends AbstractExecutorService {
    private final ExecutorService mDelegate;

    public OCDExecutorService(String name, QueueAttr attr, DispatchQueue dispatchQueue) {
        mDelegate = attr != null && attr.mConcurrent
                ? Executors.newCachedThreadPool()
                : Executors.newSingleThreadExecutor();
    }

    @Override
    public void shutdown() {
        mDelegate.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return mDelegate.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return mDelegate.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return mDelegate.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return mDelegate.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        mDelegate.execute(command);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return mDelegate.submit(task);
    }
}
