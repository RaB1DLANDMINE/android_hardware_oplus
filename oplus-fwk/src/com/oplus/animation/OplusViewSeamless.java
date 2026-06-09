package com.oplus.animation;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;

public class OplusViewSeamless {
    public static boolean finishCurrentAnimation() {
        return false;
    }

    public static boolean setSeamlessView(
            View view, Context context, Bundle bundle, AnimationCallback callback) {
        return false;
    }

    public static void skipBackAnim(Activity activity) {
    }

    public static class AnimationCallback {
        private PointF offset = new PointF();

        public void animationProgress(float progress) {
        }

        public PointF getPositionOffset() {
            return offset;
        }

        public void onAnimationEnd(boolean isExit) {
        }

        public void onAnimationStart(boolean isExit) {
        }

        public void setPositionOffset(PointF offset) {
            this.offset = offset;
        }
    }
}
