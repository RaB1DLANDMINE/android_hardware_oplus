package com.oplus.dispatch;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DispatchWorkItem implements Runnable {
    private final AtomicBoolean mEnabled = new AtomicBoolean(true);

    public void cancel(boolean mayInterruptIfRunning) {
        mEnabled.set(false);
    }

    protected boolean isEnabled() {
        return mEnabled.get();
    }
}
