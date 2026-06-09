package com.oplus.wrapper.content.pm;

import android.os.UserHandle;

public class UserInfo {
    private final android.content.pm.UserInfo userInfo;

    public UserInfo(android.content.pm.UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getId() {
        return userInfo.id;
    }

    public UserHandle getUserHandle() {
        return userInfo.getUserHandle();
    }

    public android.content.pm.UserInfo getUserInfo() {
        return userInfo;
    }

    public boolean isEnabled() {
        return userInfo.isEnabled();
    }
}
