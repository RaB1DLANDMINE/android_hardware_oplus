package android.os;

import android.content.Context;
import android.util.ArrayMap;
import android.view.KeyEvent;

/**
 * Stub for the OEM key-event manager used by OplusCamera's MainUIContainer and the
 * volume/shutter key observers (mm.b2 implements OnKeyEventObserver, mm.c2/a2 register
 * interceptors). On stock this is in oplus-framework.jar (bootclasspath); we ship it via
 * oplus.camera.stubs. No-op: register/unregister return false (not handled), so the app
 * falls back to standard KeyEvent dispatch. Replaces the brittle per-class smali
 * neutralization in extract-files.py, which was keyed to stale obfuscated names and missed
 * mm.v1 in 6.070.71.
 */
public class OplusKeyEventManager {

    public interface OnKeyEventObserver {
        void onKeyEvent(KeyEvent event);
    }

    private static final OplusKeyEventManager INSTANCE = new OplusKeyEventManager();

    public static OplusKeyEventManager getInstance() {
        return INSTANCE;
    }

    public boolean registerKeyEventObserver(Context context, OnKeyEventObserver observer, int priority) {
        return false;
    }

    public boolean unregisterKeyEventObserver(Context context, OnKeyEventObserver observer) {
        return false;
    }

    public boolean registerKeyEventInterceptor(Context context, String token, OnKeyEventObserver observer, ArrayMap map) {
        return false;
    }

    public boolean unregisterKeyEventInterceptor(Context context, String token, OnKeyEventObserver observer) {
        return false;
    }
}
