package com.oplus.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

public class OplusRenderNodeAnimator {
    public static void animateToFinalPosition(Animator animator, float finalPosition) {
        if (animator instanceof IRtAnimationTarget) {
            ((IRtAnimationTarget) animator).animateToFinalPosition(finalPosition);
        }
    }

    public static Animator createRenderValueAnimator(Animator animator, View view) {
        return animator;
    }

    public static Animator createRtAnimator(IRtAnimationTarget target, View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                target.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                target.end();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                target.cancel();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        return animator;
    }

    public static long getFrameNumber(Animator animator) {
        return -1L;
    }
}
