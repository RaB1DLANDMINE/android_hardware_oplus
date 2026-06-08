package com.oplus.shoulderpressure;

public class OplusShoulderPressureManager {
    public static final int HALL_LEFT_PRESS_GEAR = 124;
    public static final int HALL_RIGHT_PRESS_GEAR = 125;
    public static final int MECHAICAL_KEY_PARAMS = 127;
    public static final int PRESS_CHARGE_STATE = 128;
    public static final int PRESS_GAME_SWITCH_ENABLE = 126;

    public OplusShoulderPressureManager() {
    }

    public String readShoulderPressureNodeFile(int nodeFlag) {
        return null;
    }

    public String readShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag) {
        return null;
    }

    public boolean writeShoulderPressureNodeFile(int nodeFlag, String info) {
        return false;
    }

    public boolean writeShoulderPressureNodeFileByDevice(int deviceId, int nodeFlag, String info) {
        return false;
    }

    public boolean isShoulderPressureNodeSupport(int deviceId, int nodeFlag) {
        return false;
    }
}
