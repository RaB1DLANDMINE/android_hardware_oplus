package com.oplus.orms;

import android.view.MotionEvent;

import com.oplus.orms.info.OrmsCtrlDataParam;
import com.oplus.orms.info.OrmsNotifyParam;
import com.oplus.orms.info.OrmsSaParam;

import java.util.HashMap;

public class OplusResourceManager {
    private static final HashMap<String, OplusResourceManager> sInstanceCache = new HashMap<>();

    private long mNextRequest = 1;

    protected OplusResourceManager(Class clazz) {
    }

    public static synchronized OplusResourceManager getInstance(Class clazz) {
        String key = clazz == null ? "" : clazz.getName();
        OplusResourceManager instance = sInstanceCache.get(key);
        if (instance == null) {
            instance = new OplusResourceManager(clazz);
            sInstanceCache.put(key, instance);
        }
        return instance;
    }

    public long ormsSetSceneAction(OrmsSaParam ormsSaParam) {
        return mNextRequest++;
    }

    public void ormsClrSceneAction(long request) {
    }

    public void ormsSetNotification(OrmsNotifyParam ormsNotifyParam) {
    }

    public void ormsSetCtrlData(OrmsCtrlDataParam ormsCtrlDataParam) {
    }

    public void ormsClrCtrlData() {
    }

    public int ormsGetModeStatus(int mode) {
        return -1;
    }

    public long[][][] ormsGetPerfLimit() {
        return null;
    }

    public void ormsSendFling(MotionEvent ev, int duration) {
    }
}
