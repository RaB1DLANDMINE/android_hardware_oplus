package com.oplus.media;

import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.util.Log;
import android.view.Surface;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;

public class OplusHeifConverter {
    private static final int DECODE_BUFFER_SIZE = 16384;
    private static final int FTYP_BOX = 1718909296;
    private static final int HEIC_BOX = 1751476579;
    private static final long MAX_SIZE = 104857600;
    private static final int MIF1_BOX = 1835623985;
    private static final String TAG = "OplusHeifConverter_Java";

    private long m10BitObject;
    private Surface mSurface;

    static {
        Log.v(TAG, "loadLibrary");
        System.loadLibrary("oplus_heifconverter");
    }

    private static native boolean nativeCheckPPS(byte[] data);
    private static native long nativeCreateDecoder();
    private static native boolean nativeDecode(long object, InputStream inputStream, Surface surface,
            int sampleSize);
    private static native boolean nativeDecodeRegion(long object, InputStream inputStream, int left,
            int top, int width, int height, Surface surface, int sampleSize, int flag);
    private static native void nativeDestroyDecoder(long object);
    private static native HeifDecodedFrame nativeGetDecodeFrame(long object, InputStream inputStream,
            int sampleSize, boolean directBuffer);
    private static native HeifDecodedFrame nativeGetRegionDecodeFrame(long object,
            InputStream inputStream, int left, int top, int width, int height, int sampleSize,
            boolean directBuffer, int flag);
    private static native boolean nativeHeifConvert(InputStream inputStream, byte[] tempStorage,
            int quality, OutputStream outputStream, byte[] jpegStorage);
    private static native void nativeRecycle(long bufferId, long subBufferId);
    private static native boolean nativeRender(byte[] yuvData, int width, int height,
            Surface surface, int dataSpace);
    private static native boolean nativeRenderDirectBuffer(long bufferId, long subBufferId,
            int width, int height, Surface surface, int dataSpace);

    public static class HeifDecodedFrame {
        public ColorSpace m_ColorSpace;
        public long m_buffer_id;
        public long m_buffer_id_sub;
        public int m_frame_height;
        public int m_frame_width;
        public boolean m_recycled;
        public byte[] m_yuvdata;

        public HeifDecodedFrame(byte[] yuv, int width, int height, long id) {
            m_yuvdata = yuv;
            m_frame_width = width;
            m_frame_height = height;
            m_buffer_id = id;
        }

        public final boolean isRecycled() {
            return m_recycled;
        }

        public void recycle() {
            if (m_recycled) {
                return;
            }
            OplusHeifConverter.nativeRecycle(m_buffer_id, m_buffer_id_sub);
            m_yuvdata = null;
            m_recycled = true;
        }

        public boolean render(Surface surface, boolean directBuffer) {
            return render(surface, directBuffer, ColorSpace.get(ColorSpace.Named.DISPLAY_P3));
        }

        public boolean render(Surface surface, boolean directBuffer, ColorSpace colorSpace) {
            if (m_recycled) {
                return true;
            }

            int dataSpace = colorSpace != null
                    && colorSpace.getId() != ColorSpace.Named.DISPLAY_P3.ordinal()
                    ? 142671872 : 143261696;
            try {
                if (directBuffer) {
                    return OplusHeifConverter.nativeRenderDirectBuffer(m_buffer_id, m_buffer_id_sub,
                            m_frame_width, m_frame_height, surface, dataSpace);
                }
                return OplusHeifConverter.nativeRender(m_yuvdata, m_frame_width, m_frame_height,
                        surface, dataSpace);
            } catch (Exception e) {
                Log.e(TAG, "Unable to native10BitSetSurfaceYUVdata stream: " + e);
                return false;
            }
        }
    }

    public static boolean convertHeifToJpegFromPath(String pathName, int quality,
            OutputStream outStream) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(pathName);
            return convertHeifToJpegFromStream(inputStream, quality, outStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static boolean convertHeifToJpegFromStream(InputStream inputStream, int quality,
            OutputStream outStream) {
        Log.e(TAG, " convertHeif2JPEGFromStream start! quality ###" + quality);
        if (outStream == null) {
            throw new NullPointerException();
        }
        if (quality < 0 || quality > 100) {
            throw new IllegalArgumentException("quality must be 0..100");
        }

        try {
            boolean result = nativeHeifConvert(inputStream, new byte[DECODE_BUFFER_SIZE], quality,
                    outStream, new byte[4096]);
            Log.e(TAG, " ConvertHeif2JPEG result: " + result);
            return result;
        } catch (Exception e) {
            Log.e(TAG, "Unable to ConvertHeif2JPEG stream: " + e);
            return false;
        }
    }

    public int byteArrayToInt(byte[] data, int offset) {
        return (data[offset + 3] & 0xff) | ((data[offset + 2] & 0xff) << 8)
                | ((data[offset + 1] & 0xff) << 16) | ((data[offset] & 0xff) << 24);
    }

    private int getBoxInfo(HashSet<Integer> boxes) {
        boxes.add(HEIC_BOX);
        boxes.add(MIF1_BOX);
        return FTYP_BOX;
    }

    public boolean isHEIFFile(InputStream inputStream) throws IOException {
        boolean result = false;
        HashSet<Integer> boxes = new HashSet<>();
        int ftyp = getBoxInfo(boxes);
        try {
            DataInputStream data = new DataInputStream(inputStream);
            byte[] fileData = new byte[1024];
            int available = data.read(fileData);
            if (available <= 8 || byteArrayToInt(fileData, 4) != ftyp) {
                Log.d(TAG, "Not mov file.");
                return false;
            }

            int size = byteArrayToInt(fileData, 0);
            if (size <= 8) {
                Log.d(TAG, "buffer not enought.");
                return false;
            }

            int brandCount = (size - 8) / 4;
            Log.d(TAG, "brandCnt " + brandCount + " size " + size);
            for (int i = 0; i < brandCount && (i * 4) + 12 < available; i++) {
                boxes.remove(byteArrayToInt(fileData, (i * 4) + 8));
            }
            result = boxes.isEmpty();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getFormat(InputStream inputStream) throws IOException {
        return getFormatFromStream(inputStream, false);
    }

    public int getFormat(FileDescriptor fd) throws IOException {
        FileInputStream inputStream = new FileInputStream(fd);
        try {
            return getFormatFromStream(inputStream, true);
        } finally {
            inputStream.close();
        }
    }

    private int getFormatFromStream(InputStream inputStream, boolean closeBuffered)
            throws IOException {
        int len = inputStream.available();
        if (len >= MAX_SIZE) {
            Log.e(TAG, "The file is bigger than 100MB.Can't get format!");
            return 0;
        }

        byte[] data = new byte[len];
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
            int read = bis.read(data);
            if (read < 8 || byteArrayToInt(data, 4) != FTYP_BOX) {
                Log.e(TAG, "not a heif file!");
                return 0;
            }

            int size = byteArrayToInt(data, 0);
            int cur = 8 + (size - 8);
            while (cur + 8 <= len) {
                int boxSize = byteArrayToInt(data, cur);
                int boxType = byteArrayToInt(data, cur + 4);
                cur += 8;
                if (boxSize <= 0) {
                    break;
                }

                if (boxType == 1835295092 && boxSize == 1 && cur + 8 <= len) {
                    int sizeHigh = byteArrayToInt(data, cur);
                    int sizeLow = byteArrayToInt(data, cur + 4);
                    int largeSize = (sizeHigh << 8) + sizeLow;
                    cur += largeSize - 16;
                } else if (boxType == 1835365473) {
                    cur += 4;
                    Log.d(TAG, "parse meta_box cur " + cur);
                } else if (boxType == 1768977008) {
                    Log.d(TAG, "parse iprp_box cur  " + cur);
                } else if (boxType == 1768973167) {
                    Log.d(TAG, "parse ipco_box cur  " + cur);
                } else if (boxType == 1752589123) {
                    Log.d(TAG, "cur hvcC_box  " + cur);
                    if (cur + 19 <= len) {
                        int bitDepthLumaMinus8 = data[cur + 17] & 3;
                        int bitDepthChromaMinus8 = data[cur + 18] & 3;
                        if (bitDepthLumaMinus8 == 2 && bitDepthChromaMinus8 == 2) {
                            Log.d(TAG, "It is 10Bit Heif!");
                            return 1;
                        }
                    }
                    Log.d(TAG, "It is 8Bit Heif!");
                    return 0;
                } else {
                    cur += boxSize - 8;
                }
            }

            Log.d(TAG, "It is 8Bit Heif!");
            return 0;
        } finally {
            if (closeBuffered) {
                bis.close();
            }
        }
    }

    public boolean createDecoder() {
        m10BitObject = nativeCreateDecoder();
        return true;
    }

    public boolean destroyDecoder() {
        nativeDestroyDecoder(m10BitObject);
        return true;
    }

    public boolean decode(InputStream inputStream, int sampleSize, Surface surface) {
        mSurface = surface;
        if (surface == null) {
            Log.e(TAG, "sur is NULL!");
        }
        try {
            return nativeDecode(m10BitObject, inputStream, surface, sampleSize);
        } catch (Exception e) {
            Log.e(TAG, "Unable to nativeShow10BitHEIF stream: " + e);
            return false;
        }
    }

    public boolean decode(FileDescriptor fd, int sampleSize, Surface surface) {
        FileInputStream inputStream = new FileInputStream(fd);
        try {
            return decode(inputStream, sampleSize, surface);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }
        }
    }

    public HeifDecodedFrame decode(InputStream inputStream, int sampleSize, boolean directBuffer) {
        try {
            HeifDecodedFrame frame = nativeGetDecodeFrame(m10BitObject, inputStream, sampleSize,
                    directBuffer);
            if (frame != null) {
                frame.m_recycled = false;
                Log.e(TAG, "width: " + frame.m_frame_width + " height:" + frame.m_frame_height
                        + " isDirectBuffer: " + directBuffer);
            }
            return frame;
        } catch (Exception e) {
            Log.e(TAG, "Unable to nativeGet10BitYUVdata stream: " + e);
            return null;
        }
    }

    public HeifDecodedFrame decode(FileDescriptor fd, int sampleSize, boolean directBuffer)
            throws IOException {
        FileInputStream inputStream = new FileInputStream(fd);
        try {
            return decode(inputStream, sampleSize, directBuffer);
        } finally {
            inputStream.close();
        }
    }

    public boolean decodeRegion(InputStream inputStream, Rect rect, int sampleSize,
            Surface surface) {
        mSurface = surface;
        Rect target = rect;
        int flag = 0;
        if (target == null) {
            Log.e(TAG, "rect is null,decode whole image!");
            target = new Rect(0, 0, 0, 0);
            flag = 1;
        }
        try {
            return nativeDecodeRegion(m10BitObject, inputStream, target.left, target.top,
                    target.right - target.left, target.bottom - target.top, surface, sampleSize,
                    flag);
        } catch (Exception e) {
            Log.e(TAG, "Unable to nativeShow10BitHEIF stream: " + e);
            return false;
        }
    }

    public boolean decodeRegion(FileDescriptor fd, Rect rect, int sampleSize, Surface surface)
            throws IOException {
        FileInputStream inputStream = new FileInputStream(fd);
        try {
            return decodeRegion(inputStream, rect, sampleSize, surface);
        } finally {
            inputStream.close();
        }
    }

    public HeifDecodedFrame decodeRegion(InputStream inputStream, Rect rect, int sampleSize,
            boolean directBuffer) {
        Rect target = rect;
        int flag = 0;
        if (target == null) {
            Log.e(TAG, "rect is NULL!,decode whole image");
            target = new Rect(0, 0, 0, 0);
            flag = 1;
        }
        try {
            HeifDecodedFrame frame = nativeGetRegionDecodeFrame(m10BitObject, inputStream,
                    target.left, target.top, target.right - target.left,
                    target.bottom - target.top, sampleSize, directBuffer, flag);
            if (frame != null) {
                frame.m_recycled = false;
                Log.e(TAG, "width: " + frame.m_frame_width + " height: " + frame.m_frame_height);
            }
            return frame;
        } catch (Exception e) {
            Log.e(TAG, "Unable to nativeGet10BitYUVdata stream: " + e);
            return null;
        }
    }

    public HeifDecodedFrame decodeRegion(FileDescriptor fd, Rect rect, int sampleSize,
            boolean directBuffer) throws IOException {
        FileInputStream inputStream = new FileInputStream(fd);
        try {
            return decodeRegion(inputStream, rect, sampleSize, directBuffer);
        } finally {
            inputStream.close();
        }
    }
}
