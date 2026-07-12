package com.oplus.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OplusImageHdrImpl {
    private static final int ANDROID_BITMAP_COMPRESS_FORMAT_JPEG = 0;
    private static final int ANDROID_BITMAP_COMPRESS_FORMAT_PNG = 1;
    private static final int ANDROID_BITMAP_COMPRESS_FORMAT_WEBP_LOSSLESS = 4;
    private static final int ANDROID_BITMAP_COMPRESS_FORMAT_WEBP_LOSSY = 3;
    private static final int DECODE_BUFFER_SIZE = 16384;
    private static final int QUALITY_MAX = 100;
    private static final int QUALITY_MIN = 0;
    private static final String TAG = "OplusImageHdrImpl_Java";
    private static final int WORKING_COMPRESS_STORAGE = 4096;

    public static class GainmapInfo {
        public int mBaseImageType;
        public float mDisplayRatioHdr;
        public float mDisplayRatioSdr;
        public float[] mEpsilonHdr;
        public float[] mEpsilonSdr;
        public Bitmap mGainmap;
        public float[] mGainmapGamma;
        public float[] mGainmapRatioMax;
        public float[] mGainmapRatioMin;
        public float mHdrScale;
        public boolean mIsJpegR;
        public int mType;
    }

    private static native boolean nativeCompressAlpha8(
            Bitmap bitmap, int format, int quality, OutputStream stream, byte[] storage);

    private static native Bitmap nativeDecodeBaseJpeg(
            InputStream inputStream, byte[] storage, BitmapFactory.Options opts);

    private static native GainmapInfo nativeDecodeGainmapAndMetadata(
            InputStream inputStream, byte[] storage, int sampleSize);

    private static native GainmapInfo nativeDemuxFile(InputStream inputStream, byte[] storage);

    static {
        Log.v(TAG, "loadLibrary");
        System.loadLibrary("oplusImageHdrImpl");
    }

    private static int nativeCompressFormat(Bitmap.CompressFormat format) {
        if (format == Bitmap.CompressFormat.JPEG) {
            return ANDROID_BITMAP_COMPRESS_FORMAT_JPEG;
        }
        if (format == Bitmap.CompressFormat.PNG) {
            return ANDROID_BITMAP_COMPRESS_FORMAT_PNG;
        }
        if (format == Bitmap.CompressFormat.WEBP_LOSSY) {
            return ANDROID_BITMAP_COMPRESS_FORMAT_WEBP_LOSSY;
        }
        if (format == Bitmap.CompressFormat.WEBP_LOSSLESS) {
            return ANDROID_BITMAP_COMPRESS_FORMAT_WEBP_LOSSLESS;
        }
        Log.e(TAG, "format " + format + " has no corresponding native compress format!");
        return -1;
    }

    public static GainmapInfo demuxFile(FileDescriptor fd) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(fd)) {
            return nativeDemuxFile(inputStream, new byte[DECODE_BUFFER_SIZE]);
        } catch (Exception e) {
            Log.e(TAG, "Unable to parse this file: " + e);
            return null;
        }
    }

    public static GainmapInfo demuxFile(InputStream inputStream) {
        try {
            return nativeDemuxFile(inputStream, new byte[DECODE_BUFFER_SIZE]);
        } catch (Exception e) {
            Log.e(TAG, "Unable to parse this file: " + e);
            return null;
        }
    }

    public static Bitmap decodeBaseJpeg(FileDescriptor fd, BitmapFactory.Options opts)
            throws IOException {
        try (FileInputStream inputStream = new FileInputStream(fd)) {
            return nativeDecodeBaseJpeg(inputStream, new byte[DECODE_BUFFER_SIZE], opts);
        } catch (Exception e) {
            Log.e(TAG, "Unable to parse this file: " + e);
            return null;
        }
    }

    public static Bitmap decodeBaseJpeg(InputStream inputStream, BitmapFactory.Options opts) {
        try {
            return nativeDecodeBaseJpeg(inputStream, new byte[DECODE_BUFFER_SIZE], opts);
        } catch (Exception e) {
            Log.e(TAG, "Unable to parse this file: " + e);
            return null;
        }
    }

    public static GainmapInfo decodeGainmapAndMetadata(FileDescriptor fd, int sampleSize)
            throws IOException {
        try (FileInputStream inputStream = new FileInputStream(fd)) {
            return nativeDecodeGainmapAndMetadata(
                    inputStream, new byte[DECODE_BUFFER_SIZE], sampleSize);
        } catch (Exception e) {
            Log.e(TAG, "Unable to parse this file: " + e);
            return null;
        }
    }

    public static GainmapInfo decodeGainmapAndMetadata(InputStream inputStream, int sampleSize) {
        try {
            return nativeDecodeGainmapAndMetadata(
                    inputStream, new byte[DECODE_BUFFER_SIZE], sampleSize);
        } catch (Exception e) {
            Log.e(TAG, "Unable to parse this file: " + e);
            return null;
        }
    }

    public static boolean compressAlpha8(
            Bitmap bitmap, Bitmap.CompressFormat format, int quality, OutputStream stream) {
        if (bitmap == null || stream == null) {
            Log.e(TAG, "bitmap or stream is null");
            return false;
        }
        int nativeFormat = nativeCompressFormat(format);
        if (nativeFormat != ANDROID_BITMAP_COMPRESS_FORMAT_JPEG
                && nativeFormat != ANDROID_BITMAP_COMPRESS_FORMAT_PNG) {
            Log.e(TAG, "illegal format");
            return false;
        }
        if (quality < 0 || quality > 100) {
            Log.e(TAG, "quality must be 0..100");
            return false;
        }
        try {
            return nativeCompressAlpha8(
                    bitmap, nativeFormat, quality, stream, new byte[WORKING_COMPRESS_STORAGE]);
        } catch (Exception e) {
            Log.e(TAG, "Unable to compress this bitmap: " + e);
            return false;
        }
    }
}
