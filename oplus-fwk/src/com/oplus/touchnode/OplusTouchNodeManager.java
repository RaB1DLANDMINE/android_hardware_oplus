package com.oplus.touchnode;

public class OplusTouchNodeManager {
    public static final int BASELINE_RESULT_NODE = 14;
    public static final int BASELINE_TEST_NODE = 3;
    public static final int BLACK_SCREEN_RESULT_NODE = 15;
    public static final int BLACK_SCREEN_TEST_NODE = 13;
    public static final int CALIBRATION_NODE = 4;
    public static final int CHARGE_DETECT_NODE = 10;
    public static final int COORDINATE_NODE = 2;
    public static final int DEBUG_BASELINE_NODE = 18;
    public static final int DEBUG_DELTA_NODE = 17;
    public static final int DEBUG_HEALTH_MONITOR_NODE = 20;
    public static final int DOUBLE_TAP_ENABLE_NODE = 1;
    public static final int DOUBLE_TAP_INDEP_NODE = 21;
    public static final int HEAD_SET_DETECT_NODE = 12;
    public static final int HOVER_SELFDATA_NODE = 19;
    public static final int KERNEL_GRIP_HANDLE_NODE = 8;
    public static final int OPLUS_TP_DIRECTION_NODE = 5;
    public static final int OPLUS_TP_LIMIT_ENABLE_NODE = 7;
    public static final int OPLUS_TP_LIMIT_WHITELIST_NODE = 6;
    public static final int REPORT_RATE_WHITE_LIST_NODE = 9;
    public static final int TOUCH_OPTIMIZED_TIME_NODE = 22;
    public static final int TP_AGING_TEST_NODE = 16;
    public static final int WIRELESS_CHARGE_DETECT_NODE = 11;

    public interface OplusTouchEventCallback {
    }

    private static final class InstanceHolder {
        private static final OplusTouchNodeManager INSTANCE = new OplusTouchNodeManager();
    }

    private OplusTouchNodeManager() {
    }

    public static OplusTouchNodeManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public boolean writeNodeFileByDevice(int deviceId, int nodeFlag, String info) {
        return false;
    }
}
