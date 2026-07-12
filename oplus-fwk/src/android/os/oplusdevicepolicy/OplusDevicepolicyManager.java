package android.os.oplusdevicepolicy;

import java.util.Collections;
import java.util.List;

public class OplusDevicepolicyManager {
    private static final OplusDevicepolicyManager INSTANCE = new OplusDevicepolicyManager();

    private OplusDevicepolicyManager() {
    }

    public static OplusDevicepolicyManager getInstance() {
        return INSTANCE;
    }

    public boolean addList(String name, List<String> value, int type) {
        return true;
    }

    public boolean clearData(int type) {
        return true;
    }

    public boolean clearList(int type) {
        return true;
    }

    public boolean getBoolean(String name, int type, boolean def) {
        return def;
    }

    public String getData(String name, int type) {
        return null;
    }

    public List<String> getList(String name, int type) {
        return Collections.emptyList();
    }

    public boolean registerOplusDevicepolicyObserver(
            String name, OplusDevicePolicyObserver observer) {
        return true;
    }

    public boolean removeData(String name, int type) {
        return true;
    }

    public boolean removeList(String name, int type) {
        return true;
    }

    public boolean removePartListData(String name, List<String> value, int type) {
        return true;
    }

    public boolean setData(String name, String value, int type) {
        return true;
    }

    public boolean setList(String name, List<String> value, int type) {
        return true;
    }

    public boolean unregisterOplusDevicePolicyObserver(OplusDevicePolicyObserver observer) {
        return true;
    }

    public interface OplusDevicePolicyObserver {
        void onOplusDevicePolicyUpdate(String name, List<String> value);

        void onOplusDevicePolicyUpdate(String name, String value);
    }
}
