package com.oplus.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Gainmap;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OplusImageHdrImpl {
    private static final String TAG = "OplusImageHdrImpl";

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

    public static GainmapInfo demuxFile(FileDescriptor fd) throws IOException {
        return decodeGainmapAndMetadata(fd, 1);
    }

    public static GainmapInfo demuxFile(InputStream inputStream) {
        return decodeGainmapAndMetadata(inputStream, 1);
    }

    public static Bitmap decodeBaseJpeg(FileDescriptor fd, BitmapFactory.Options opts)
            throws IOException {
        return BitmapFactory.decodeFileDescriptor(fd, null, opts);
    }

    public static Bitmap decodeBaseJpeg(InputStream inputStream, BitmapFactory.Options opts) {
        return BitmapFactory.decodeStream(inputStream, null, opts);
    }

    public static GainmapInfo decodeGainmapAndMetadata(FileDescriptor fd, int sampleSize)
            throws IOException {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = Math.max(1, sampleSize);
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd, null, opts);
        return fromBitmap(bitmap);
    }

    public static GainmapInfo decodeGainmapAndMetadata(InputStream inputStream, int sampleSize) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = Math.max(1, sampleSize);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, opts);
        return fromBitmap(bitmap);
    }

    public static boolean compressAlpha8(
            Bitmap bitmap, Bitmap.CompressFormat format, int quality, OutputStream stream) {
        if (bitmap == null || format == null || stream == null) {
            Log.e(TAG, "bitmap, format, or stream is null");
            return false;
        }
        if (quality < 0 || quality > 100) {
            Log.e(TAG, "quality must be 0..100");
            return false;
        }
        return bitmap.compress(format, quality, stream);
    }

    private static GainmapInfo fromBitmap(Bitmap bitmap) {
        if (bitmap == null || !bitmap.hasGainmap()) {
            return null;
        }

        Gainmap gainmap = bitmap.getGainmap();
        GainmapInfo info = new GainmapInfo();
        info.mGainmap = gainmap.getGainmapContents();
        info.mGainmapRatioMin = gainmap.getRatioMin();
        info.mGainmapRatioMax = gainmap.getRatioMax();
        info.mGainmapGamma = gainmap.getGamma();
        info.mEpsilonSdr = gainmap.getEpsilonSdr();
        info.mEpsilonHdr = gainmap.getEpsilonHdr();
        info.mDisplayRatioSdr = gainmap.getMinDisplayRatioForHdrTransition();
        info.mDisplayRatioHdr = gainmap.getDisplayRatioForFullHdr();
        info.mHdrScale = gainmap.getDisplayRatioForFullHdr();
        info.mType = gainmap.getGainmapDirection();
        info.mBaseImageType = 0;
        info.mIsJpegR = true;
        return info;
    }
}
