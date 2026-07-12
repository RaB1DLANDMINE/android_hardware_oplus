package android.os;

import vendor.oplus.hardware.performance.ProcMemStatRet;

public class PerformanceManager {
    public static String getHICpuLoading() {
        return "";
    }

    public static String readJankCpuInfo() {
        return "";
    }

    public static ProcMemStatRet readMemoryByPids(int[] pids, int flags) {
        return new ProcMemStatRet(pids);
    }

    public static void enableMultiThreadOptimize() {
    }

    public static void disableMultiThreadOptimize() {
    }
}
