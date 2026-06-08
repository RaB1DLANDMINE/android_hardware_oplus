package com.oplus.wrapper.view;

import android.graphics.Rect;
import android.graphics.Region;

public class ViewTreeObserver {
    public interface OnComputeInternalInsetsListener {
        void onComputeInternalInsets(InternalInsetsInfo internalInsetsInfo);
    }

    public ViewTreeObserver(android.view.ViewTreeObserver viewTreeObserver) {
    }

    public void addOnComputeInternalInsetsListener(OnComputeInternalInsetsListener listener) {
    }

    public void removeOnComputeInternalInsetsListener(OnComputeInternalInsetsListener victim) {
    }

    public static final class InternalInsetsInfo {
        public static final int TOUCHABLE_INSETS_FRAME = 0;
        public static final int TOUCHABLE_INSETS_CONTENT = 1;
        public static final int TOUCHABLE_INSETS_VISIBLE = 2;
        public static final int TOUCHABLE_INSETS_REGION = 3;

        InternalInsetsInfo() {
        }

        public void setTouchableInsets(int val) {
        }

        public Region getTouchableRegion() {
            return null;
        }

        public Rect getContentInsets() {
            return null;
        }

        public Rect getVisibleInsets() {
            return null;
        }
    }
}
