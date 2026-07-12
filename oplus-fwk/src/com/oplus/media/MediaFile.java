package com.oplus.media;

import android.webkit.MimeTypeMap;

import java.util.HashMap;
import java.util.Locale;

public class MediaFile {
    public static final int FILE_TYPE_MP3 = 1;
    public static final int FILE_TYPE_M4A = 2;
    public static final int FILE_TYPE_WAV = 3;
    public static final int FILE_TYPE_AMR = 4;
    public static final int FILE_TYPE_AWB = 5;
    public static final int FILE_TYPE_WMA = 6;
    public static final int FILE_TYPE_OGG = 7;
    public static final int FILE_TYPE_AAC = 8;
    public static final int FILE_TYPE_MKA = 9;
    public static final int FILE_TYPE_FLAC = 10;
    public static final int FILE_TYPE_MID = 11;
    public static final int FILE_TYPE_SMF = 12;
    public static final int FILE_TYPE_IMY = 13;
    public static final int FILE_TYPE_MP4 = 21;
    public static final int FILE_TYPE_M4V = 22;
    public static final int FILE_TYPE_3GPP = 23;
    public static final int FILE_TYPE_3GPP2 = 24;
    public static final int FILE_TYPE_WMV = 25;
    public static final int FILE_TYPE_ASF = 26;
    public static final int FILE_TYPE_MKV = 27;
    public static final int FILE_TYPE_MP2TS = 28;
    public static final int FILE_TYPE_AVI = 29;
    public static final int FILE_TYPE_WEBM = 30;
    public static final int FILE_TYPE_JPEG = 31;
    public static final int FILE_TYPE_GIF = 32;
    public static final int FILE_TYPE_PNG = 33;
    public static final int FILE_TYPE_BMP = 34;
    public static final int FILE_TYPE_WBMP = 35;
    public static final int FILE_TYPE_WEBP = 36;
    public static final int FILE_TYPE_HEIF = 37;
    public static final int FILE_TYPE_M3U = 41;
    public static final int FILE_TYPE_PLS = 42;
    public static final int FILE_TYPE_WPL = 43;
    public static final int FILE_TYPE_HTTPLIVE = 44;
    public static final int FILE_TYPE_FL = 51;
    public static final int FILE_TYPE_TEXT = 100;
    public static final int FILE_TYPE_HTML = 101;
    public static final int FILE_TYPE_PDF = 102;
    public static final int FILE_TYPE_XML = 103;
    public static final int FILE_TYPE_MS_WORD = 104;
    public static final int FILE_TYPE_MS_EXCEL = 105;
    public static final int FILE_TYPE_MS_POWERPOINT = 106;
    public static final int FILE_TYPE_ZIP = 107;
    public static final int FILE_TYPE_MP2PS = 200;
    public static final int FILE_TYPE_QT = 201;
    public static final int FILE_TYPE_DNG = 300;
    public static final int FILE_TYPE_CR2 = 301;
    public static final int FILE_TYPE_NEF = 302;
    public static final int FILE_TYPE_NRW = 303;
    public static final int FILE_TYPE_ARW = 304;
    public static final int FILE_TYPE_RW2 = 305;
    public static final int FILE_TYPE_ORF = 306;
    public static final int FILE_TYPE_RAF = 307;
    public static final int FILE_TYPE_PEF = 308;
    public static final int FILE_TYPE_SRW = 309;
    public static final int FILE_TYPE_APE = 1001;
    public static final int FILE_TYPE_MP2 = 1002;
    public static final int FILE_TYPE_CUE = 1003;
    public static final int FILE_TYPE_RA = 1004;
    public static final int FILE_TYPE_FLV = 1101;
    public static final int FILE_TYPE_RV = 1102;
    public static final int FILE_TYPE_MOV = 1103;
    public static final int FILE_TYPE_M2TS = 1104;
    public static final int FILE_TYPE_RAR = 10001;
    public static final int FILE_TYPE_JAR = 10002;
    public static final int FILE_TYPE_APK = 10011;
    public static final int FILE_TYPE_CHM = 10021;
    public static final int FILE_TYPE_CSV = 10022;
    public static final int FILE_TYPE_ICS = 10023;
    public static final int FILE_TYPE_VCF = 10024;
    public static final int FILE_TYPE_VCS = 10025;
    public static final int FILE_TYPE_EBK = 10026;
    public static final int FILE_TYPE_EPUB = 10027;

    public static final int MEDIA_TYPE_COMPRESS = 10001;
    public static final int MEDIA_TYPE_APK = 10002;
    public static final int MEDIA_TYPE_DOC = 10003;

    private static final String MIME_TYPE_DEFAULT = "application/octet-stream";
    private static final HashMap<String, MediaFileType> FILE_TYPE_MAP = new HashMap<>();
    private static final HashMap<String, Integer> MIME_TYPE_MAP = new HashMap<>();

    public static class MediaFileType {
        public final int fileType;
        public final String mimeType;

        public MediaFileType(int fileType, String mimeType) {
            this.fileType = fileType;
            this.mimeType = mimeType;
        }
    }

    static {
        addFileType("MP3", FILE_TYPE_MP3, "audio/mpeg");
        addFileType("MPGA", FILE_TYPE_MP3, "audio/mpeg");
        addFileType("M4A", FILE_TYPE_M4A, "audio/mp4");
        addFileType("WAV", FILE_TYPE_WAV, "audio/x-wav");
        addFileType("AMR", FILE_TYPE_AMR, "audio/amr");
        addFileType("3GPP", FILE_TYPE_MID, "audio/3gpp");
        addFileType("AWB", FILE_TYPE_AWB, "audio/amr-wb");
        addFileType("WMA", FILE_TYPE_WMA, "audio/x-ms-wma");
        addFileType("OGG", FILE_TYPE_OGG, "audio/ogg");
        addFileType("OGA", FILE_TYPE_OGG, "application/ogg");
        addFileType("AAC", FILE_TYPE_AAC, "audio/aac");
        addFileType("MKA", FILE_TYPE_MKA, "audio/x-matroska");
        addFileType("MID", FILE_TYPE_MID, "audio/midi");
        addFileType("MIDI", FILE_TYPE_MID, "audio/midi");
        addFileType("SMF", FILE_TYPE_SMF, "audio/sp-midi");
        addFileType("IMY", FILE_TYPE_IMY, "audio/imelody");
        addFileType("FLAC", FILE_TYPE_FLAC, "audio/flac");
        addFileType("APE", FILE_TYPE_APE, "audio/ape");
        addFileType("MP2", FILE_TYPE_MP2, "audio/mpeg");
        addFileType("CUE", FILE_TYPE_CUE, "audio/cue");

        addFileType("MPEG", FILE_TYPE_MP4, "video/mpeg");
        addFileType("MPG", FILE_TYPE_MP4, "video/mpeg");
        addFileType("MP4", FILE_TYPE_MP4, "video/mp4");
        addFileType("M4V", FILE_TYPE_M4V, "video/mp4");
        addFileType("3GP", FILE_TYPE_3GPP, "video/3gpp");
        addFileType("3G2", FILE_TYPE_3GPP2, "video/3gpp2");
        addFileType("WMV", FILE_TYPE_WMV, "video/x-ms-wmv");
        addFileType("ASF", FILE_TYPE_ASF, "video/x-ms-asf");
        addFileType("MKV", FILE_TYPE_MKV, "video/x-matroska");
        addFileType("TS", FILE_TYPE_MP2TS, "video/mp2ts");
        addFileType("AVI", FILE_TYPE_AVI, "video/avi");
        addFileType("WEBM", FILE_TYPE_WEBM, "video/webm");
        addFileType("FLV", FILE_TYPE_FLV, "video/x-flv");
        addFileType("F4V", FILE_TYPE_FLV, "video/x-flv");
        addFileType("MOV", FILE_TYPE_MOV, "video/quicktime");
        addFileType("M2TS", FILE_TYPE_M2TS, "video/m2ts");

        addFileType("JPG", FILE_TYPE_JPEG, "image/jpeg");
        addFileType("JPEG", FILE_TYPE_JPEG, "image/jpeg");
        addFileType("GIF", FILE_TYPE_GIF, "image/gif");
        addFileType("PNG", FILE_TYPE_PNG, "image/png");
        addFileType("BMP", FILE_TYPE_BMP, "image/bmp");
        addFileType("WBMP", FILE_TYPE_WBMP, "image/vnd.wap.wbmp");
        addFileType("WEBP", FILE_TYPE_WEBP, "image/webp");
        addFileType("HEIC", FILE_TYPE_HEIF, "image/heif");
        addFileType("HEIF", FILE_TYPE_HEIF, "image/heif");
        addFileType("DNG", FILE_TYPE_DNG, "image/x-adobe-dng");
        addFileType("CR2", FILE_TYPE_CR2, "image/x-canon-cr2");
        addFileType("NEF", FILE_TYPE_NEF, "image/x-nikon-nef");
        addFileType("NRW", FILE_TYPE_NRW, "image/x-nikon-nrw");
        addFileType("ARW", FILE_TYPE_ARW, "image/x-sony-arw");
        addFileType("RW2", FILE_TYPE_RW2, "image/x-panasonic-rw2");
        addFileType("ORF", FILE_TYPE_ORF, "image/x-olympus-orf");
        addFileType("RAF", FILE_TYPE_RAF, "image/x-fuji-raf");
        addFileType("PEF", FILE_TYPE_PEF, "image/x-pentax-pef");
        addFileType("SRW", FILE_TYPE_SRW, "image/x-samsung-srw");

        addFileType("M3U", FILE_TYPE_M3U, "audio/x-mpegurl");
        addFileType("PLS", FILE_TYPE_PLS, "audio/x-scpls");
        addFileType("WPL", FILE_TYPE_WPL, "application/vnd.ms-wpl");
        addFileType("M3U8", FILE_TYPE_HTTPLIVE, "application/vnd.apple.mpegurl");
        addFileType("FL", FILE_TYPE_FL, "application/x-android-drm-fl");

        addFileType("TXT", FILE_TYPE_TEXT, "text/plain");
        addFileType("HTM", FILE_TYPE_HTML, "text/html");
        addFileType("HTML", FILE_TYPE_HTML, "text/html");
        addFileType("PDF", FILE_TYPE_PDF, "application/pdf");
        addFileType("XML", FILE_TYPE_XML, "text/xml");
        addFileType("DOC", FILE_TYPE_MS_WORD, "application/msword");
        addFileType("DOCX", FILE_TYPE_MS_WORD, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        addFileType("XLS", FILE_TYPE_MS_EXCEL, "application/vnd.ms-excel");
        addFileType("XLSX", FILE_TYPE_MS_EXCEL, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        addFileType("PPT", FILE_TYPE_MS_POWERPOINT, "application/vnd.ms-powerpoint");
        addFileType("PPTX", FILE_TYPE_MS_POWERPOINT, "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        addFileType("ZIP", FILE_TYPE_ZIP, "application/zip");
        addFileType("RAR", FILE_TYPE_RAR, "application/rar");
        addFileType("JAR", FILE_TYPE_JAR, "application/java-archive");
        addFileType("APK", FILE_TYPE_APK, "application/vnd.android.package-archive");
        addFileType("CHM", FILE_TYPE_CHM, "application/x-expandedbook");
        addFileType("CSV", FILE_TYPE_CSV, "text/comma-separated-values");
        addFileType("ICS", FILE_TYPE_ICS, "text/calendar");
        addFileType("VCF", FILE_TYPE_VCF, "text/x-vcard");
        addFileType("VCS", FILE_TYPE_VCS, "text/x-vcalendar");
        addFileType("EBK2", FILE_TYPE_EBK, "text/x-expandedbook");
        addFileType("EBK3", FILE_TYPE_EBK, "text/x-expandedbook");
        addFileType("EPUB", FILE_TYPE_EPUB, "application/epub+zip");
    }

    private static void addFileType(String extension, int fileType, String mimeType) {
        FILE_TYPE_MAP.put(extension, new MediaFileType(fileType, mimeType));
        MIME_TYPE_MAP.put(mimeType, fileType);
    }

    public static boolean isAudioFileType(int fileType) {
        return (fileType >= FILE_TYPE_MP3 && fileType <= FILE_TYPE_MID)
                || (fileType >= FILE_TYPE_MID && fileType <= FILE_TYPE_IMY)
                || (fileType >= FILE_TYPE_APE && fileType <= FILE_TYPE_RA);
    }

    public static boolean isVideoFileType(int fileType) {
        return (fileType >= FILE_TYPE_MP4 && fileType <= FILE_TYPE_WEBM)
                || (fileType >= FILE_TYPE_MP2PS && fileType <= FILE_TYPE_QT)
                || (fileType >= FILE_TYPE_FLV && fileType <= FILE_TYPE_M2TS);
    }

    public static boolean isImageFileType(int fileType) {
        return (fileType >= FILE_TYPE_JPEG && fileType <= FILE_TYPE_HEIF)
                || (fileType >= FILE_TYPE_DNG && fileType <= FILE_TYPE_SRW);
    }

    public static boolean isDocFileType(int fileType) {
        return (fileType >= FILE_TYPE_CHM && fileType <= FILE_TYPE_EPUB)
                || (fileType >= FILE_TYPE_TEXT && fileType <= FILE_TYPE_MS_POWERPOINT
                && fileType != FILE_TYPE_XML);
    }

    public static boolean isApkMimeType(String mimeType) {
        return "application/vnd.android.package-archive".equals(normalizeMimeType(mimeType));
    }

    public static MediaFileType getFileType(String path) {
        String extension = getFileExtension(path);
        if (extension == null) {
            return null;
        }
        return FILE_TYPE_MAP.get(extension.toUpperCase(Locale.ROOT));
    }

    public static String getMimeTypeForFile(String path) {
        MediaFileType type = getFileType(path);
        if (type != null) {
            return type.mimeType;
        }

        String extension = getFileExtension(path);
        String mimeType = extension == null
                ? null
                : MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase(Locale.ROOT));
        return mimeType != null ? mimeType : MIME_TYPE_DEFAULT;
    }

    public static int getFileTypeForMimeType(String mimeType) {
        Integer value = MIME_TYPE_MAP.get(normalizeMimeType(mimeType));
        return value != null ? value : 0;
    }

    private static String getFileExtension(String path) {
        if (path == null) {
            return null;
        }
        int lastDot = path.lastIndexOf('.');
        if (lastDot < 0 || lastDot == path.length() - 1) {
            return null;
        }
        return path.substring(lastDot + 1);
    }

    private static String normalizeMimeType(String mimeType) {
        return mimeType == null ? "" : mimeType.toLowerCase(Locale.ROOT);
    }
}
