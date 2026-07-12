package vendor.oplus.hardware.performance;

public class ProcMemStatRet {
    public ProcMemStat[] arr_ms;

    public ProcMemStatRet() {
        this(null);
    }

    public ProcMemStatRet(int[] pids) {
        if (pids == null) {
            arr_ms = new ProcMemStat[0];
            return;
        }
        arr_ms = new ProcMemStat[pids.length];
        for (int i = 0; i < pids.length; i++) {
            ProcMemStat stat = new ProcMemStat();
            stat.pid = pids[i];
            arr_ms[i] = stat;
        }
    }
}
