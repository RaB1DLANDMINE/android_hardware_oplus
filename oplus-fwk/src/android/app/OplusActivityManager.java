package android.app;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.oplus.app.IOplusProtectConnection;
import java.util.ArrayList;
import java.util.List;

public class OplusActivityManager {
    private static final OplusActivityManager INSTANCE = new OplusActivityManager();

    public OplusActivityManager() {
    }

    public static OplusActivityManager getInstance() {
        return INSTANCE;
    }

    public static List getFilteredTasks(int maxNum, boolean filterOnlyVisibleRecents) {
        return new ArrayList();
    }

    public void addBackgroundRestrictedInfo(String callerPkg, List targetPkgList) {
    }

    public void addPreventIndulgeList(List packageNames) {
    }

    public List getAllTopAppInfos() {
        return new ArrayList();
    }

    public List getAllTopApps() {
        return new ArrayList();
    }

    public Bundle getConfigInfo(String configName, int flag, int userId) throws RemoteException {
        return null;
    }

    public List<String> getGlobalPkgWhiteList(int type) throws RemoteException {
        return new ArrayList<>();
    }

    public List<String> getGlobalProcessWhiteList() throws RemoteException {
        return new ArrayList<>();
    }

    public List<String> getStageProtectListFromPkg(String callerPkg, int type) throws RemoteException {
        return new ArrayList<>();
    }

    public List<String> getStageProtectListFromPkgAsUser(String callerPkg, int type, int userId) throws RemoteException {
        return new ArrayList<>();
    }

    public List<String> getStageProtectList(int type) throws RemoteException {
        return new ArrayList<>();
    }

    public List<String> getStageProtectListAsUser(int type, int userId) throws RemoteException {
        return new ArrayList<>();
    }

    public void addStageProtectInfo(
            String callerPkg,
            String pkg,
            List<String> processList,
            String reason,
            long timeout,
            IOplusProtectConnection connection) throws RemoteException {
    }

    public void removeStageProtectInfo(String pkg, String callerPkg) throws RemoteException {
    }

    public List getTaskPkgList(int taskId) {
        return new ArrayList();
    }

    public ComponentName getTopActivityComponentName() {
        return null;
    }

    public void handleAppFromControlCenter(String packageName, int uid) {
    }

    public boolean isAppCallRefuseMode() {
        return false;
    }

    public boolean putConfigInfo(String configName, Bundle bundle, int flag, int userId) throws RemoteException {
        return true;
    }

    public boolean requestDeviceFolded(int folded, boolean enableSecDisplay) {
        return false;
    }

    public void setAllowLaunchApps(List packageNames) {
    }

    public void setAppCallRefuseMode(boolean enabled) {
    }

    public void setChildSpaceMode(boolean enabled) {
    }

    public void setPreventIndulgeController(com.oplus.app.IOplusAppStartController controller) {
    }

    public void setAppStartMonitorController(com.oplus.app.IOplusAppStartController controller) {
    }

    public void startActivity(Intent intent) {
    }
}
