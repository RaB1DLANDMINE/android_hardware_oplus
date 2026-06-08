package android.os;

import java.io.File;

/**
 * Stub for OEM environment paths. OplusCamera locates oplus partition config dirs through these.
 * Returns the conventional Oplus mount points; callers tolerate non-existent dirs.
 */
public class OplusBaseEnvironment {
    public static File getOplusProductDirectory() {
        return new File("/my_product");
    }

    public static File getOplusCustomDirectory() {
        return new File("/my_custom");
    }

    public static File getOplusEngineerDirectory() {
        return new File("/my_engineering");
    }

    public static File getOplusVersionDirectory() {
        return new File("/my_manifest");
    }
}
