package com.oppo.media;

public class MediaFile {
    public static class MediaFileType {
        public final int fileType;
        public final String mimeType;

        public MediaFileType(int fileType, String mimeType) {
            this.fileType = fileType;
            this.mimeType = mimeType;
        }
    }

    public static com.oppo.media.MediaFile.MediaFileType getFileType(String path) {
        com.oplus.media.MediaFile.MediaFileType type = com.oplus.media.MediaFile.getFileType(path);
        return type == null ? null : new MediaFileType(type.fileType, type.mimeType);
    }

    public static String getMimeTypeForFile(String path) {
        return com.oplus.media.MediaFile.getMimeTypeForFile(path);
    }

    public static boolean isApkMimeType(String mimeType) {
        return com.oplus.media.MediaFile.isApkMimeType(mimeType);
    }

    public static boolean isAudioFileType(int fileType) {
        return com.oplus.media.MediaFile.isAudioFileType(fileType);
    }

    public static boolean isDocFileType(int fileType) {
        return com.oplus.media.MediaFile.isDocFileType(fileType);
    }

    public static boolean isImageFileType(int fileType) {
        return com.oplus.media.MediaFile.isImageFileType(fileType);
    }

    public static boolean isVideoFileType(int fileType) {
        return com.oplus.media.MediaFile.isVideoFileType(fileType);
    }
}
