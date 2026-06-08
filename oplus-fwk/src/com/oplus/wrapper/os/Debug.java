package com.oplus.wrapper.os;

public final class Debug {
    private Debug() {
    }

    public static String getCallers(int depth) {
        return android.os.Debug.getCallers(depth);
    }

    public static class MemoryInfo {
        public static final int OTHER_GRAPHICS = 14;
        public static final int OTHER_GL = 15;
        public static final int OTHER_GL_DEV = 4;

        public MemoryInfo(android.os.Debug.MemoryInfo memoryInfo) {
        }

        public int getTotalUss() {
            return 0;
        }

        public int getNativeSwappedOutPss() {
            return 0;
        }

        public void setNativeSwappedOutPss(int pss) {
        }

        public int getOtherPss(int which) {
            return 0;
        }

        public int getOtherSwappedOutPss(int which) {
            return 0;
        }

        public int getDalvikSwappedOutPss() {
            return 0;
        }

        public void setDalvikSwappedOutPss(int pss) {
        }

        public int getSummaryGraphics() {
            return 0;
        }
    }
}
