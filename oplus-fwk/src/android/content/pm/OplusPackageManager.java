package android.content.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.IntentSender;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;

import com.oplus.app.OplusAppDynamicFeatureData;
import com.oplus.content.IOplusFeatureActionObserver;
import com.oplus.content.IOplusFeatureMapObserver;
import com.oplus.content.IOplusFeatureObserver;
import com.oplus.content.OplusRemovableAppInfo;
import com.oplus.ota.OplusSystemUpdateInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OplusPackageManager {
    public static final int BROADCAST_PACKAGE_ADDED_RESTRICTED = 1;
    public static final int BROADCAST_PACKAGE_NONE_RESTRICTED = 0;
    public static final int FLAG_APP_DATA_CE = 2;
    public static final int FLAG_APP_DATA_DE = 1;
    public static final int FLAG_APP_DATA_EXTERNAL_DATA = 16;
    public static final int FLAG_APP_DATA_EXTERNAL_MEDIA = 32;
    public static final int FLAG_APP_DATA_EXTERNAL_OBB = 64;
    public static final int FLAG_GET_SHORTCUTS_INTENT = 524288;
    public static final int INSTALL_FROM_OPLUS_ADB_INSTALLER = 268435456;
    public static final int INSTALL_SPEED_BACKGROUND = Integer.MIN_VALUE;
    public static final int INSTALL_SPEED_CPU_HIGH = 1073741824;
    public static final int INSTALL_SPEED_CPU_MID = 536870912;
    public static final String LIB_COMPAT_ACTIVE_MODE_DEFAULT = "default_active";
    public static final String LIB_COMPAT_ACTIVE_MODE_ENGINEER = "engineer_active";
    public static final String LIB_COMPAT_ACTIVE_MODE_INSTALLER = "installer_active";
    public static final String LIB_COMPAT_ACTIVE_MODE_RUS = "rus_active";
    public static final int MATCH_OPLUS_FREEZE_APP = 1073741824;
    public static final int OPLUS_DONT_KILL_APP = 268435456;
    public static final int OPLUS_FREEZE_FLAG_AUTO = 2;
    public static final int OPLUS_FREEZE_FLAG_MANUAL = 1;
    public static final int OPLUS_UNFREEZE_FLAG_NORMAL = 1;
    public static final int OPLUS_UNFREEZE_FLAG_TEMP = 2;
    public static final int RE_INSTALL_DUPLICATE_PERMISSION = 1;
    public static final int STATE_OPLUS_FREEZE_FREEZED = 2;
    public static final int STATE_OPLUS_FREEZE_NORMAL = 0;
    public static final int STATE_OPLUS_FREEZE_TEMP_UNFREEZED = 1;

    private static final String TAG = "OplusPackageManager";
    private static OplusPackageManager sInstance;

    private final Context mContext;

    public OplusPackageManager(Context context) {
        mContext = context;
    }

    public OplusPackageManager() {
        mContext = null;
    }

    public static OplusPackageManager getOplusPackageManager(Context context) {
        if (sInstance == null) {
            sInstance = new OplusPackageManager(context);
        }
        return sInstance;
    }

    public boolean isClosedSuperFirewall() {
        return false;
    }

    public boolean isFullFunctionMode() throws RemoteException {
        return false;
    }

    public Drawable getApplicationIconCacheAll(ApplicationInfo info) {
        return loadIcon(info);
    }

    public Drawable getApplicationIconCache(ApplicationInfo info) {
        return loadIcon(info);
    }

    public Drawable getApplicationIconCacheOrignal(ApplicationInfo info) {
        return loadIcon(info);
    }

    public Drawable getApplicationIconCache(String packageName) throws PackageManager.NameNotFoundException {
        if (mContext == null) {
            Log.e(TAG, "Context is null");
            return null;
        }
        return mContext.getPackageManager().getApplicationIcon(packageName);
    }

    public Drawable getApplicationIconCacheOrignal(String packageName) throws PackageManager.NameNotFoundException {
        return getApplicationIconCache(packageName);
    }

    public Drawable getActivityIconCache(ComponentName componentName) throws PackageManager.NameNotFoundException {
        if (mContext == null) {
            Log.e(TAG, "Context is null");
            return null;
        }
        return mContext.getPackageManager().getActivityIcon(componentName);
    }

    public boolean prohibitChildInstallation(int userId, boolean isInstall) throws RemoteException {
        return false;
    }

    public int oplusFreezePackage(String pkgName, int userId, int freezeFlag, int flag, String callingPkg)
            throws RemoteException {
        return 0;
    }

    public int oplusUnFreezePackage(String pkgName, int userId, int freezeFlag, int flag, String callingPkg)
            throws RemoteException {
        return 0;
    }

    public int getOplusFreezePackageState(String pkgName, int userId) throws RemoteException {
        return STATE_OPLUS_FREEZE_NORMAL;
    }

    public boolean inOplusFreezePackageList(String pkgName, int userId) throws RemoteException {
        return false;
    }

    public List<String> getOplusFreezedPackageList(int userId) throws RemoteException {
        return Collections.emptyList();
    }

    public int getOplusPackageFreezeFlag(String pkgName, int userId) throws RemoteException {
        return 0;
    }

    public boolean loadRegionFeature(String name) throws RemoteException {
        return false;
    }

    public FeatureInfo[] getOplusSystemAvailableFeatures() throws RemoteException {
        return new FeatureInfo[0];
    }

    public boolean isSecurePayApp(String name) throws RemoteException {
        return false;
    }

    public boolean isSystemDataApp(String packageName) throws RemoteException {
        return false;
    }

    public boolean inPmsWhiteList(int type, String verifyStr, List<String> defaultList) {
        return defaultList != null && defaultList.contains(verifyStr);
    }

    public List<String> getRemovableAppList() throws RemoteException {
        return Collections.emptyList();
    }

    public List<OplusRemovableAppInfo> getRemovedAppInfos() throws RemoteException {
        return Collections.emptyList();
    }

    public List<OplusRemovableAppInfo> getRemovableAppInfos() throws RemoteException {
        return Collections.emptyList();
    }

    public OplusRemovableAppInfo getRemovableAppInfo(String packageName) throws RemoteException {
        return null;
    }

    public boolean restoreRemovableApp(String packageName, IntentSender sender, Bundle bundle)
            throws RemoteException {
        return false;
    }

    public boolean isSupportSessionWrite() throws RemoteException {
        return false;
    }

    public List<String> getCptListByType(int tag) throws RemoteException {
        return Collections.emptyList();
    }

    public void sendCptUpload(String pkgName, String point) throws RemoteException {
    }

    public boolean inCptWhiteList(int type, String verifyStr) {
        return false;
    }

    public boolean inOplusStandardWhiteList(String filterName, int type, String verifyStr)
            throws RemoteException {
        return false;
    }

    public void sendMapCommonDcsUpload(String logTag, String eventId, Map map) throws RemoteException {
    }

    public List<ApplicationInfo> getIconPackList() {
        return Collections.emptyList();
    }

    public void dynamicDetectApp(OplusAppDynamicFeatureData featureData) throws RemoteException {
    }

    public boolean isDetectApp(String packageName) throws RemoteException {
        return false;
    }

    public List<String> getDetectAppList() throws RemoteException {
        return Collections.emptyList();
    }

    public boolean isCrossVersionUpdate() throws RemoteException {
        return false;
    }

    public List<String> getNotInstalledSystemApps() throws RemoteException {
        return Collections.emptyList();
    }

    public List<String> getValidAppList() throws RemoteException {
        return Collections.emptyList();
    }

    public List<String> getAppListFromPartition(String partition) throws RemoteException {
        return Collections.emptyList();
    }

    public OplusSystemUpdateInfo getSystemUpdateInfo() throws RemoteException {
        return new OplusSystemUpdateInfo();
    }

    public boolean fixupAppData(String pkgName, int flags) throws RuntimeException {
        return false;
    }

    public boolean fixupAppData(String pkgName, String relativePath, int flags) throws RuntimeException {
        return false;
    }

    public String getMigMappingPkgName(boolean findOldNameByNew, String refPkgName) throws RuntimeException {
        return null;
    }

    public List<String> getUninstallableAppConfig(int type) {
        return Collections.emptyList();
    }

    public boolean inUninstallableAppConfig(int type, String pkgName) {
        return false;
    }

    public boolean setCustomizeDefaultApp(String roleName, String packageName) throws RemoteException {
        return false;
    }

    public void removeCustomizeDefaultApp(String roleName) throws RemoteException {
    }

    public String getCustomizeDefaultApp(String roleName) throws RemoteException {
        return null;
    }

    public Map getAllCustomizeDefaultApps() {
        return Collections.emptyMap();
    }

    public boolean isPackageDefaultApp(String packageName) {
        return false;
    }

    public void clearPackageDefaultApps(String packageName) {
    }

    public void clearAllPackageDefaultApps() {
    }

    public boolean isFreezeEnabled() {
        return false;
    }

    public void setFreezeEnable(boolean enabled) {
    }

    public int getPackageFreezeState(String pkg, UserHandle userHandle) {
        return 0;
    }

    public int getPackageFreezeState(String pkg, int userHandle) {
        return 0;
    }

    public int getPackageFreezeUserSetting(String pkg, UserHandle userHandle) {
        return 0;
    }

    public int getPackageFreezeUserSetting(String pkg, int userHandle) {
        return 0;
    }

    public void setPackageFreezeState(String pkg, int state, UserHandle userHandle) {
    }

    public void setPackageFreezeState(String pkg, int state, int userHandle) {
    }

    public void setPackageFreezeUserSetting(String pkg, int setting, UserHandle userHandle) {
    }

    public void setPackageFreezeUserSetting(String pkg, int setting, int userHandle) {
    }

    public List<String> getFreezedApplicationList(UserHandle userHandle) {
        return Collections.emptyList();
    }

    public List<String> getFreezedApplicationList(int userHandle) {
        return Collections.emptyList();
    }

    public List<String> getUserSettingFreezeableApplicationList(UserHandle userHandle) {
        return Collections.emptyList();
    }

    public List<String> getUserSettingFreezeableApplicationList(int userHandle) {
        return Collections.emptyList();
    }

    public boolean hasFeatureIPC(String featureName, int featureID) throws RemoteException {
        return false;
    }

    public boolean enableFeature(String featureName) throws RemoteException {
        return false;
    }

    public boolean disableFeature(String featureName) throws RemoteException {
        return false;
    }

    public boolean enableFeatureMap(String featureName, int featureID) throws RemoteException {
        return false;
    }

    public boolean disableFeatureMap(String featureName, int featureID) throws RemoteException {
        return false;
    }

    public void notifyFeaturesUpdate(String action, String actionValue) throws RemoteException {
    }

    public void notifyFeaturesMapUpdate(String action, String actionValue, int featureID) throws RemoteException {
    }

    public boolean registerFeatureObserverInner(List<String> features, IOplusFeatureObserver observer)
            throws RemoteException {
        return false;
    }

    public boolean unregisterFeatureObserverInner(IOplusFeatureObserver observer) throws RemoteException {
        return false;
    }

    public boolean registerFeatureMapObserverInner(List<String> featureList, int featureID,
            IOplusFeatureMapObserver observer) throws RemoteException {
        return false;
    }

    public boolean unregisterFeatureMapObserverInner(int featureID, IOplusFeatureMapObserver observer)
            throws RemoteException {
        return false;
    }

    public boolean registerFeatureActionObserverInner(IOplusFeatureActionObserver observer)
            throws RemoteException {
        return false;
    }

    public boolean unregisterFeatureActionObserverInner(IOplusFeatureActionObserver observer)
            throws RemoteException {
        return false;
    }

    public static Bundle appDetailsForwardToMarket(int type, Bundle bundle) {
        return null;
    }

    public static boolean markResolveIntentForMarket(String token) {
        return false;
    }

    public int getAbiCheckResult(String packageName) {
        return -1;
    }

    public List<String> queryIncompatibleApplist() {
        return new ArrayList<>();
    }

    public boolean setMarketRecommendPause(long milliseconds) {
        return false;
    }

    public boolean isTranslatorWhitelistApp(String packageName) {
        return false;
    }

    public boolean isStringGcEnabled(String packageName) {
        return false;
    }

    public void setStringGcEnabled(String packageName, boolean enabled) {
    }

    private Drawable loadIcon(ApplicationInfo info) {
        if (mContext == null || info == null) {
            Log.e(TAG, "Context or ApplicationInfo is null");
            return null;
        }
        return info.loadIcon(mContext.getPackageManager());
    }
}
