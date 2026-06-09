package com.oplus.view;

public interface IRtAnimationTarget {
    void animateToFinalPosition(float finalPosition);

    void cancel();

    boolean doFrame(long frameTime);

    void end();

    boolean isRunning();

    void setAnimationHandler();

    void skipToEnd();

    void start();
}
