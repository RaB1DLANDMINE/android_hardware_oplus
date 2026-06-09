package com.oplus.multiuser;

import java.util.ArrayList;
import java.util.List;

public class OplusMultiUserManager {
    public static final int FLAG_MULTI_SYSTEM = 0x20000000;
    public static final int FLAG_STUDY_USER = 0x40000000;

    private static final OplusMultiUserManager INSTANCE = new OplusMultiUserManager();

    public OplusMultiUserManager() {
    }

    public static OplusMultiUserManager getInstance() {
        return INSTANCE;
    }

    public static List<String> getForbiddenPkgList() {
        return new ArrayList<>();
    }

    public boolean hasMultiSystemUser() {
        return false;
    }

    public int getMultiSystemUserId() {
        return -10000;
    }

    public boolean isMultiSystemUserId(int userId) {
        return false;
    }
}
