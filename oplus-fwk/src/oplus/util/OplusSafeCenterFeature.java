package oplus.util;

public class OplusSafeCenterFeature {
    private static boolean sAssociateStartFeature = true;

    public static boolean isAssociationStartEnabled() {
        return sAssociateStartFeature;
    }

    public static void setAssociationStartFeature(boolean enabled) {
        sAssociateStartFeature = enabled;
    }

    public static boolean isLaunchRecordEnabled() {
        return true;
    }
}
