package com.oplus.util;

import android.content.Context;

public class OplusNetworkUtil {
    public static final int AIRPLANE_MODE_ON_STR = 0;
    public static final int MOBILE_AND_WLAN_NETWORK_NOT_CONNECT_STR = 1;
    public static final int NETWORK_CONNECT_OK_STR = -1;
    public static final int NO_NETWORK_CONNECT_STR = 3;
    public static final String TAG = "OplusNetworkUtil";
    public static final int WLAN_NEED_LOGIN_STR = 2;

    public static boolean isWifiConnected(Context context) {
        return false;
    }

    public static boolean isMobileDataConnected(Context context) {
        return false;
    }
}
