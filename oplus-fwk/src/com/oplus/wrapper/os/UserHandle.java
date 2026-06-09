package com.oplus.wrapper.os;

public final class UserHandle {
    public static final android.os.UserHandle CURRENT = android.os.UserHandle.CURRENT;
    public static final android.os.UserHandle OWNER = android.os.UserHandle.of(0);
    public static final android.os.UserHandle SYSTEM = android.os.UserHandle.SYSTEM;
    public static final int USER_ALL = -1;
    public static final int USER_CURRENT = -2;
    public static final int USER_NULL = -10000;
    public static final int USER_SYSTEM = 0;

    private final android.os.UserHandle mUserHandle;

    private UserHandle() {
        mUserHandle = null;
    }

    public UserHandle(android.os.UserHandle userHandle) {
        mUserHandle = userHandle;
    }

    public UserHandle(int userId) {
        mUserHandle = android.os.UserHandle.of(userId);
    }

    public static android.os.UserHandle create(int userId) {
        return android.os.UserHandle.of(userId);
    }

    public static int getIdentifier(android.os.UserHandle target) {
        return target.getIdentifier();
    }

    public static boolean isSystem(android.os.UserHandle target) {
        return target.isSystem();
    }

    public int getIdentifier() {
        return mUserHandle != null ? mUserHandle.getIdentifier() : USER_NULL;
    }

    public android.os.UserHandle getUserHandle() {
        return mUserHandle;
    }

    public boolean isSystem() {
        return mUserHandle != null && mUserHandle.isSystem();
    }

    public static int myUserId() {
        return android.os.UserHandle.myUserId();
    }

    public static int getUserId(int uid) {
        return android.os.UserHandle.getUserId(uid);
    }

    public static int getAppId(int uid) {
        return android.os.UserHandle.getAppId(uid);
    }

    public static int getUid(int userId, int appId) {
        return android.os.UserHandle.getUid(userId, appId);
    }

    public static android.os.UserHandle of(int userId) {
        return android.os.UserHandle.of(userId);
    }

    public static boolean isApp(int uid) {
        return android.os.UserHandle.isApp(uid);
    }

    public static int getCallingUserId() {
        return android.os.UserHandle.getCallingUserId();
    }
}
