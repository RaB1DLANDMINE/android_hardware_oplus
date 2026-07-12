package com.oplus.blur;

/**
 * Inert no-op stub for the native bokeh/segmentation preview helper.
 * The real implementation is backed by proprietary native libs not present
 * on this port; all entry points return safe failure defaults.
 */
public class OplusBlurPreview {
    private static final OplusBlurPreview INSTANCE = new OplusBlurPreview();

    private OplusBlurPreview() {
    }

    public static OplusBlurPreview getInstance() {
        return INSTANCE;
    }

    public long bokehPreviewInit(int a, int b, int c) {
        return 0;
    }

    public int bokehPreviewProcess(int a, int b, int c, int d, int[] e) {
        return -1;
    }

    public int bokehPreviewGetMaskTexture(byte[] a, int[] b, boolean c) {
        return -1;
    }

    public int bokehPreviewTextureCopy(int a, int[] b, boolean c) {
        return -1;
    }

    public int bokehPreviewTextureResize(int a, int b, int c, int d, boolean e) {
        return -1;
    }

    public int bokehPreviewSetParam(int a, float b) {
        return -1;
    }

    public int bokehPreviewSetDebug(boolean a) {
        return -1;
    }

    public int bokehPreviewDestory() {
        return -1;
    }

    public String bokehPreviewGetVersion() {
        return "";
    }

    public int[] segInit(String a, String b, String c, String d, int e, int f) {
        return null;
    }

    public int segExecute(byte[] a, byte[] b, int c, int d, int e, int f, int g, int[] h, int[] i, int[] j, int[] k, int[] l) {
        return -1;
    }

    public int segLogLevel(int a) {
        return -1;
    }

    public int segUnInit() {
        return -1;
    }

    public String segGetVersion() {
        return "";
    }
}
