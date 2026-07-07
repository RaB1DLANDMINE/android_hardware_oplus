package android.os;

import android.content.Context;
import android.view.KeyEvent;

public class OplusKeyEventManager {
    public static final String TAG = "OplusKeyEventManager";

    public static final int INTERCEPT_ALWAYS = 0;
    public static final int INTERCEPT_ONCE = 1;

    public static final int LISTEN_ALL_KEY_EVENT = 0;
    public static final int LISTEN_POWER_KEY_EVENT = 1;
    public static final int LISTEN_VOLUME_UP_KEY_EVENT = 2;
    public static final int LISTEN_VOLUME_DOWN_KEY_EVENT = 4;
    public static final int LISTEN_MENU_KEY_EVENT = 8;
    public static final int LISTEN_HOME_KEY_EVENT = 16;
    public static final int LISTEN_BACK_KEY_EVENT = 32;
    public static final int LISTEN_F4_KEY_EVENT = 64;
    public static final int LISTEN_CAMERA_KEY_EVENT = 128;
    public static final int LISTEN_HEADSETHOOK_KEY_EVENT = 1024;
    public static final int LISTEN_VOLUME_MUTE_KEY_EVENT = 2048;
    public static final int LISTEN_APP_SWITCH_KEY_EVENT = 4096;
    public static final int LISTEN_WAKEUP_KEY_EVENT = 8192;
    public static final int LISTEN_BRIGHTNESS_UP_KEY_EVENT = 16384;
    public static final int LISTEN_BRIGHTNESS_DOWN_KEY_EVENT = 32768;
    public static final int LISTEN_ENDCALL_KEY_EVENT = 65536;
    public static final int LISTEN_SLEEP_KEY_EVENT = 131072;
    public static final int LISTEN_ACTION_BUTTON_SINGLE_TAP_KEY_EVENT = 262144;
    public static final int LISTEN_ACTION_BUTTON_LONG_PRESS_KEY_EVENT = 524288;
    public static final int LISTEN_LINGXI_GAME_KEY_EVENT = 16777216;
    public static final int LISTEN_SHOULDER_DOWN_KEY_EVENT = 33554432;
    public static final int LISTEN_SHOULDER_UP_KEY_EVENT = 67108864;
    public static final int LISTEN_LINGXI_NO_GAME_KEY_EVENT = 134217728;

    public int mVersion = 1;

    private static volatile OplusKeyEventManager sInstance;

    private OplusKeyEventManager() {
    }

    public static OplusKeyEventManager getInstance() {
        if (sInstance == null) {
            synchronized (OplusKeyEventManager.class) {
                if (sInstance == null) {
                    sInstance = new OplusKeyEventManager();
                }
            }
        }
        return sInstance;
    }

    public boolean registerKeyEventObserver(Context context, OnKeyEventObserver observer, int listenFlag) {
        return false;
    }

    public boolean unregisterKeyEventObserver(Context context, OnKeyEventObserver observer) {
        return false;
    }

    public int getVersion() {
        return mVersion;
    }

    public interface OnKeyEventObserver {
        void onKeyEvent(KeyEvent event);
    }
}
