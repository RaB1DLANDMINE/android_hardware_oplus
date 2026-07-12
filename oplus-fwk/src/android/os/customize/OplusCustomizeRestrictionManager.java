package android.os.customize;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import java.util.Collections;
import java.util.List;

public class OplusCustomizeRestrictionManager {
    private static final OplusCustomizeRestrictionManager INSTANCE =
            new OplusCustomizeRestrictionManager();

    private OplusCustomizeRestrictionManager() {
    }

    public static OplusCustomizeRestrictionManager getInstance(Context context) {
        return INSTANCE;
    }

    public void addAppInstallPackageBlacklist(int mode, List<String> packageNames) {
    }

    public void addAppInstallPackageWhitelist(int mode, List<String> packageNames) {
    }

    public boolean addDisallowedClearDataCacheApps(List<String> packageNames) {
        return true;
    }

    public boolean allowWifiCellularNetwork(ComponentName admin, String iccId) {
        return true;
    }

    public void applyQSRestriction(String key, int value) {
    }

    public void disableQSRestriction(String key, int value) {
    }

    public boolean disableWifiSar() {
        return false;
    }

    public int getAirplanePolices(ComponentName admin) {
        return 0;
    }

    public List<String> getAppInstallPackageList(int mode) {
        return Collections.emptyList();
    }

    public int getAppInstallRestrictionPolicies() {
        return 0;
    }

    public List<String> getAppUninstallationPackageList(int mode) {
        return Collections.emptyList();
    }

    public int getAppUninstallationPolicies() {
        return 0;
    }

    public List<String> getApplicationDisabledInLauncherOrRecentTask(int userId) {
        return Collections.emptyList();
    }

    public List<String> getBluetoothDisabledProfiles() {
        return Collections.emptyList();
    }

    public int getCameraPolicies() {
        return 0;
    }

    public boolean getClipboardStatus() {
        return true;
    }

    public int getDefaultDataCard(ComponentName admin) {
        return 0;
    }

    public List<String> getDisallowedClearDataCacheApps() {
        return Collections.emptyList();
    }

    public boolean getFileSharedDisabled() {
        return false;
    }

    public boolean getForbidRecordScreenState() {
        return false;
    }

    public int getGpsPolicies(ComponentName admin) {
        return 0;
    }

    public String getLocalBluetoothAddress() {
        return null;
    }

    public String getLocalBtRandomAddress() {
        return null;
    }

    public int getMobileDataMode(ComponentName admin) {
        return 0;
    }

    public int getNfcPolicies(ComponentName admin) {
        return 0;
    }

    public long getPasswordExpirationTimeout(ComponentName admin) {
        return 0L;
    }

    public int getPasswordNumSequenceMaxLength() {
        return 0;
    }

    public int getPasswordRepeatMaxLength() {
        return 0;
    }

    public boolean getPowerDisable() {
        return false;
    }

    public boolean getQSRestrictionState(String key, int userId) {
        return false;
    }

    public int getQSRestrictionValue(String key) {
        return 0;
    }

    public long getRequiredStrongAuthTime(ComponentName admin) {
        return 0L;
    }

    public int getSideBarPolicies(ComponentName admin) {
        return 0;
    }

    public int getSlot1DataConnectivityDisabled(ComponentName admin) {
        return 0;
    }

    public int getSlot2DataConnectivityDisabled(ComponentName admin) {
        return 0;
    }

    public boolean getSplitScreenDisable(ComponentName admin) {
        return false;
    }

    public int getSystemUpdatePolicies(ComponentName admin) {
        return 0;
    }

    public int getTorchPolicies() {
        return 0;
    }

    public int getUnlockByFacePolicies(ComponentName admin) {
        return 0;
    }

    public int getUnlockByFingerprintPolicies(ComponentName admin) {
        return 0;
    }

    public int getUserPasswordPolicies(ComponentName admin) {
        return 0;
    }

    public int getWifiAssistantPolicies(ComponentName admin) {
        return 0;
    }

    public float getWifiSarPwrDbm() {
        return 0.0f;
    }

    public float getWifiSarPwrMw() {
        return 0.0f;
    }

    public List<String> getWlanAllowListWithoutScanLimit(ComponentName admin) {
        return Collections.emptyList();
    }

    public boolean isAdbDisabled(ComponentName admin) {
        return false;
    }

    public boolean isAndroidAnimationDisabled() {
        return false;
    }

    public boolean isAndroidBeamDisabled(ComponentName admin) {
        return false;
    }

    public boolean isAppInCustomVoipRecordList(String packageName) {
        return false;
    }

    public boolean isAppLockDisabled() {
        return false;
    }

    public boolean isBackButtonDisabled() {
        return false;
    }

    public boolean isBiometricDisabled() {
        return false;
    }

    public boolean isBluetoothConnectableDisabled() {
        return false;
    }

    public boolean isBluetoothDataTransferDisabled() {
        return false;
    }

    public boolean isBluetoothDisabled() {
        return false;
    }

    public boolean isBluetoothEnabled() {
        return true;
    }

    public boolean isBluetoothOutGoingCallDisabled() {
        return false;
    }

    public boolean isBluetoothPairingDisabled() {
        return false;
    }

    public boolean isBluetoothRandomEnabled() {
        return false;
    }

    public boolean isBluetoothTetheringDisabled() {
        return false;
    }

    public boolean isChangePictorialDisabled(ComponentName admin) {
        return false;
    }

    public boolean isChangeWallpaperDisabled(ComponentName admin) {
        return false;
    }

    public boolean isCustomizeDozeModeDisabled() {
        return false;
    }

    public boolean isDataRoamingDisabled() {
        return false;
    }

    public boolean isDataSyncDisabled() {
        return false;
    }

    public boolean isDiscoverableDisabled() {
        return false;
    }

    public boolean isEchoPasswordDisabled() {
        return false;
    }

    public boolean isExternalStorageDisabled() {
        return false;
    }

    public boolean isFindMyPhoneDisabled() {
        return false;
    }

    public boolean isFloatTaskDisabled(ComponentName admin) {
        return false;
    }

    public boolean isHomeButtonDisabled() {
        return false;
    }

    public boolean isLanguageChangeDisabled(ComponentName admin) {
        return false;
    }

    public boolean isLimitedDiscoverableDisabled() {
        return false;
    }

    public boolean isLocationBluetoothScanningDisabled() {
        return false;
    }

    public boolean isLongPressLauncherDisabled() {
        return false;
    }

    public boolean isLongPressVolumeUpDisabled() {
        return false;
    }

    public boolean isMmsDisabled() {
        return false;
    }

    public boolean isMmsSendReceiveDisabled() {
        return false;
    }

    public boolean isMultiAppSupport() {
        return true;
    }

    public boolean isNFCDisabled(ComponentName admin) {
        return false;
    }

    public boolean isNFCTurnOn(ComponentName admin) {
        return true;
    }

    public boolean isNavigationBarDisabled() {
        return false;
    }

    public boolean isNavigationModeRevertible() {
        return true;
    }

    public boolean isPowerSavingModeDisabled(ComponentName admin) {
        return false;
    }

    public boolean isPrivateSafeDisabled() {
        return false;
    }

    public boolean isSafeModeDisabled() {
        return false;
    }

    public boolean isSettingsApplicationDisabled(ComponentName admin) {
        return false;
    }

    public boolean isSleepByPowerButtonDisabled(ComponentName admin) {
        return false;
    }

    public boolean isSleepStandbyOptimizationDisabled() {
        return false;
    }

    public boolean isSmsReceiveDisabled() {
        return false;
    }

    public boolean isSmsSendDisabled() {
        return false;
    }

    public boolean isSuperPowerSavingModeDisabled() {
        return false;
    }

    public boolean isSwipeUpUnlockDisabled() {
        return false;
    }

    public boolean isTaskButtonDisabled() {
        return false;
    }

    public boolean isUSBDataDisabled() {
        return false;
    }

    public boolean isUSBFileTransferDisabled() {
        return false;
    }

    public boolean isUSBOtgDisabled() {
        return false;
    }

    public boolean isUnknownSourceAppInstallDisabled(ComponentName admin) {
        return false;
    }

    public boolean isUnlockByFaceDisabled(ComponentName admin) {
        return false;
    }

    public boolean isUnlockByFingerprintDisabled(ComponentName admin) {
        return false;
    }

    public boolean isUsbDebugSwitchDisabled(ComponentName admin) {
        return false;
    }

    public boolean isUsbTetheringDisabled() {
        return false;
    }

    public boolean isVoiceDisabled(ComponentName admin) {
        return false;
    }

    public boolean isVoiceIncomingDisabled(ComponentName admin, int slotId) {
        return false;
    }

    public boolean isVoiceOutgoingDisabled(ComponentName admin, int slotId) {
        return false;
    }

    public boolean isWifiDisabled(ComponentName admin) {
        return false;
    }

    public boolean isWifiOpen(ComponentName admin) {
        return true;
    }

    public boolean isWifiRandomMacForceDisable(ComponentName admin) {
        return false;
    }

    public void openCloseNFC(ComponentName admin, boolean enable) {
    }

    public boolean removeDisallowedClearDataCacheApps(List<String> packageNames) {
        return true;
    }

    public void setAdbDisabled(ComponentName admin, boolean disabled) {
    }

    public boolean setAirplanePolices(ComponentName admin, int policies) {
        return true;
    }

    public boolean setAndroidAnimationDisabled(boolean disabled) {
        return true;
    }

    public boolean setAndroidBeamDisabled(ComponentName admin, boolean disabled) {
        return true;
    }

    public void setAppInstallRestrictionPolicies(int policies) {
    }

    public void setAppLockDisabled(boolean disabled) {
    }

    public void setAppUninstallationPolicies(int policies, List<String> packages) {
    }

    public void setApplicationDisabledInLauncherOrRecentTask(List<String> packageNames, int disabled) {
    }

    public void setBackButtonDisabled(boolean disabled) {
    }

    public void setBiometricDisabled(boolean disabled) {
    }

    public boolean setBluetoothConnectableDisabled(boolean disabled) {
        return true;
    }

    public boolean setBluetoothDataTransferDisable(boolean disabled) {
        return true;
    }

    public void setBluetoothDisabled(boolean disabled) {
    }

    public boolean setBluetoothDisabledProfiles(List<String> profiles) {
        return true;
    }

    public void setBluetoothEnabled(boolean enabled) {
    }

    public boolean setBluetoothOutGoingCallDisable(boolean disabled) {
        return true;
    }

    public boolean setBluetoothPairingDisable(boolean disabled) {
        return true;
    }

    public boolean setBluetoothRandomEnabled(boolean enabled) {
        return true;
    }

    public boolean setBluetoothTetheringDisable(boolean disabled) {
        return true;
    }

    public boolean setCameraPolicies(int policies) {
        return true;
    }

    public void setChangePictorialDisable(ComponentName admin, boolean disabled) {
    }

    public void setChangeWallpaperDisable(ComponentName admin, boolean disabled) {
    }

    public void setClipboardEnabled(boolean enabled) {
    }

    public boolean setCustomizeDozeModeDisabled(boolean disabled) {
        return true;
    }

    public boolean setDataRoamingDisabled(boolean disabled) {
        return true;
    }

    public boolean setDataSyncDisabled(boolean disabled) {
        return true;
    }

    public Bundle setDefaultDataCard(ComponentName admin, int slotId) {
        return Bundle.EMPTY;
    }
}
