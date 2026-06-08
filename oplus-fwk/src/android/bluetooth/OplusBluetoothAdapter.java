package android.bluetooth;

/**
 * Stub for the OEM bluetooth adapter extension. OplusCamera's CameraManager reflectively
 * calls getOplusBluetoothAdapter().isBluetoothRecordConnected() to decide audio routing for
 * video recording. On stock this is in oplus-framework.jar (bootclasspath); shipped here via
 * oplus.camera.stubs. No-op: report no SCO record device connected.
 */
public class OplusBluetoothAdapter {

    private static final OplusBluetoothAdapter INSTANCE = new OplusBluetoothAdapter();

    public static OplusBluetoothAdapter getOplusBluetoothAdapter() {
        return INSTANCE;
    }

    public boolean isBluetoothRecordConnected() {
        return false;
    }
}
