package com.oplus.media;

import java.io.FileDescriptor;

/**
 * Inert no-op stub. HEIF/HEIC UltraHDR capture is disabled on this port via
 * ro.camera.disableHeicUltraHDR=1, so these methods are never expected to run.
 */
public class OplusHeifWriter {
    public OplusHeifWriter() {
    }

    public boolean createPrimaryImage(int a, int b, int c, int d, int e, int f, int g) {
        return false;
    }

    public boolean processPrimaryImage(byte[] in, byte[] out, FileDescriptor fd) {
        return false;
    }

    public void destory() {
    }
}
