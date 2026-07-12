package com.oplus.dispatch;

public final class OTask implements Runnable {
    private final Runnable mWork;

    OTask(Runnable work) {
        mWork = work;
    }

    @Override
    public void run() {
        if (mWork != null) {
            mWork.run();
        }
    }
}
