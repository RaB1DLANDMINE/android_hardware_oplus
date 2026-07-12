package com.oplus.globaldrag;

import android.content.ClipData;
import android.os.Bundle;
import android.view.SurfaceControl;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class DragAndDropDispatcherManager {
    private static final DragAndDropDispatcherManager INSTANCE = new DragAndDropDispatcherManager();

    private final Map<IDragAndDropListenerCallback, Boolean> mCallbacks =
            Collections.synchronizedMap(new WeakHashMap<>());

    private DragAndDropDispatcherManager() {
    }

    public static DragAndDropDispatcherManager getInstance() {
        return INSTANCE;
    }

    public void registerDragAndDropListener(String pkgName, IDragAndDropListenerCallback callback) {
        if (callback != null) {
            mCallbacks.put(callback, Boolean.TRUE);
        }
    }

    public void unregisterDragAndDropListener(String pkgName, IDragAndDropListenerCallback callback) {
        if (callback != null) {
            mCallbacks.remove(callback);
        }
    }

    public interface IDragAndDropListenerCallback {
        void prePerformDrag(
                String packageName, SurfaceControl surfaceControl, Bundle bundle, ClipData clipData);

        void postPerformDrag();

        void preReportDropResult(String packageName, boolean consumed);

        void postReportDropResult();

        void postCancelDragAndDrop();

        void postEndDrag();
    }
}
