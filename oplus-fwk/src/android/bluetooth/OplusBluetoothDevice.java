package android.bluetooth;

/** Stub for OEM bluetooth-device extension. OplusCamera reads getOplusBluetoothClass(). No-op. */
public class OplusBluetoothDevice {
    public OplusBluetoothDevice(BluetoothDevice device) {
    }

    public int getOplusBluetoothClass() {
        return 0;
    }
}
