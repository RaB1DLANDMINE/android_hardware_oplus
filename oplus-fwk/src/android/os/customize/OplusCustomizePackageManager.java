package android.os.customize;

import android.content.Context;
import android.os.Bundle;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OplusCustomizePackageManager {
    private static final OplusCustomizePackageManager INSTANCE = new OplusCustomizePackageManager();

    private OplusCustomizePackageManager() {
    }

    public static OplusCustomizePackageManager getInstance(Context context) {
        return INSTANCE;
    }

    public void addDisabledDeactivateMdmPackages(List<String> packageNames) {
    }

    public void addDisallowedUninstallPackages(List<String> packageNames) {
    }

    public boolean clearAllSuperWhiteList() {
        return true;
    }

    public void clearAppData(String packageName) {
    }

    public boolean clearSuperWhiteList(List<String> packageNames) {
        return true;
    }

    public boolean getAdbInstallUninstallDisabled() {
        return false;
    }

    public List<String> getAllInstallSysAppList() {
        return Collections.emptyList();
    }

    public List<String> getClearAppName() {
        return Collections.emptyList();
    }

    public Map<String, String> getContainOplusCertificatePackages() {
        return Collections.emptyMap();
    }

    public String getCustomizeDefaultApp(String role) {
        return null;
    }

    public List<String> getDetachableInstallSysAppList() {
        return Collections.emptyList();
    }

    public List<String> getDisabledDeactivateMdmPackages() {
        return Collections.emptyList();
    }

    public List<String> getDisallowUninstallPackageList() {
        return Collections.emptyList();
    }

    public Bundle getInstallSysAppBundle() {
        return Bundle.EMPTY;
    }

    public List<String> getPrivInstallSysAppList() {
        return Collections.emptyList();
    }

    public List<String> getSuperWhiteList() {
        return Collections.emptyList();
    }

    public boolean isDisabledDeactivateMdmPackage(String packageName) {
        return false;
    }

    public boolean isOplusCertificatePackage(String packageName) {
        return false;
    }

    public void removeAllDisabledDeactivateMdmPackages() {
    }

    public void removeAllDisallowedUninstallPackages() {
    }

    public void removeCustomizeDefaultApp(String role) {
    }

    public void removeDisabledDeactivateMdmPackages(List<String> packageNames) {
    }

    public void removeDisallowedUninstallPackages(List<String> packageNames) {
    }

    public void setAdbInstallUninstallDisabled(boolean disabled) {
    }

    public boolean setCustomizeDefaultApp(String role, String packageName) {
        return true;
    }

    public void setInstallSysAppBundle(Bundle bundle) {
    }

    public boolean setSuperWhiteList(List<String> packageNames) {
        return true;
    }
}
