package com.oplus.multiapp;

import com.oplus.wrapper.content.pm.UserInfo;

import java.util.Collections;
import java.util.List;

public class OplusMultiAppManager {
    public static final String ACTION_MULTI_APP_ALIAS_CHANGED = "oplus.intent.action.MULTI_APP_RENAME";
    public static final String ACTION_MULTI_APP_CONFIG_CHANGED = "oplus.intent.action.MULTI_APP_CONFIG_CHANGED";
    public static final String ACTION_MULTI_APP_HIDE_ALERT_DIALOG = "oplus.intent.action.MULTI_APP_HIDE_ALERT_DIALOG";
    public static final String ACTION_MULTI_APP_PACKAGE_ADDED = "oplus.intent.action.MULTI_APP_PACKAGE_ADDED";
    public static final String ACTION_MULTI_APP_PACKAGE_REMOVED = "oplus.intent.action.MULTI_APP_PACKAGE_REMOVED";
    public static final String ACTION_REMOVE_MULTIAPP_COMPLETED = "oplus.intent.action.REMOVE_MULTIAPP_COMPLETED";
    public static final int CHECK_MULTI_APP_USER = 2;
    public static final int DEFAULT_ACCESS = 0;
    public static final String EXTRA_ALIAS = "name";
    public static final String EXTRA_PACKAGE_NAME = "pkg";
    public static final String EXTRA_STATUS = "extra_status";
    public static final int LIST_OTHER_MULTI_APP = 2;
    public static final int LIST_RECOMMEND_MULTI_APP = 1;
    public static final int LIST_TYPE_ACROSS_AUTHORITY = 4;
    public static final int LIST_TYPE_ALLOWED = 1;
    public static final int LIST_TYPE_CREATED = 0;
    public static final int LIST_TYPE_INSTALLED = 3;
    public static final int LIST_TYPE_RELATED = 2;
    public static final int MAIN_APP_ACCESS = 1;
    public static final int MULTI_APP_STATUS_ADD = 1;
    public static final int MULTI_APP_STATUS_REMOVE = -1;
    public static final int REMOVE_MULTI_APP_USER = 3;
    public static final int RESTORE_MULTI_APP_USER = 4;
    public static final int RESULT_CHECK_ERROR_NO_RUNNING = -4;
    public static final int RESULT_CHECK_ERROR_REMOVE_MULTI_APP_USER = -7;
    public static final int RESULT_CHECK_ERROR_RUNNING_LOCKED = -5;
    public static final int RESULT_CHECK_ERROR_VOLD_CORRUPT = -6;
    public static final int RESULT_CHECK_NO_ERROR = 0;
    public static final int RESULT_ERROR_NOT_ALLOW_ADD = -4;
    public static final int RESULT_ERROR_NOT_SUPPORT = -2;
    public static final int RESULT_ERROR_NO_SPACE = -3;
    public static final int RESULT_FAILED = -1;
    public static final int RESULT_RESTORE_ERROR_NEED_RESET = -8;
    public static final int RESULT_SUCCESS = 1;
    public static final int USER_ID_MULTI_APP = 999;
    public static final String VOLUME_MAIN = "ace-0";
    public static final String VOLUME_MAIN_PATH = "/storage/ace-0";
    public static final String VOLUME_MULTI_APP = "ace-999";
    public static final String VOLUME_MULTI_APP_PATH = "/storage/ace-999";
    private static final OplusMultiAppManager INSTANCE = new OplusMultiAppManager();

    private OplusMultiAppManager() {
    }

    public static OplusMultiAppManager getInstance() {
        return INSTANCE;
    }

    public boolean isMultiAppSupport() {
        return false;
    }

    public int getMaxCreateNum() {
        return 0;
    }

    public int setMultiAppStatus(String pkgName, int status) {
        return RESULT_ERROR_NOT_SUPPORT;
    }

    public List<String> getMultiAppList(int type) {
        return Collections.emptyList();
    }

    public List<UserInfo> getMultiAppUserInfoList() {
        return Collections.emptyList();
    }

    public String getMultiAppAlias(String pkgName) {
        return null;
    }

    public boolean setMultiAppAlias(String pkgName, String alias) {
        return false;
    }

    public int setMultiAppAliasInner(String pkgName, String alias) {
        return RESULT_FAILED;
    }

    public int getMultiAppAccessMode(String pkgName) {
        return DEFAULT_ACCESS;
    }

    public boolean setMultiAppAccessMode(String pkgName, int accessMode) {
        return false;
    }

    public int setMultiAppAccessModeInner(String pkgName, int accessMode) {
        return RESULT_FAILED;
    }
}
