package com.oplus.wrapper.content.res;

import com.oplus.wrapper.app.WindowConfiguration;

public class Configuration {
    @Deprecated
    public Configuration(android.content.res.Configuration configuration) {
    }

    public static WindowConfiguration getWindowConfiguration(android.content.res.Configuration target) {
        return null;
    }

    @Deprecated
    public WindowConfiguration getWindowConfiguration() {
        return null;
    }

    public static boolean getUserSetLocale(android.content.res.Configuration target) {
        return false;
    }

    @Deprecated
    public boolean getUserSetLocale() {
        return false;
    }

    public static void setUserSetLocale(android.content.res.Configuration target, boolean userSetLocale) {
    }

    @Deprecated
    public void setUserSetLocale(boolean userSetLocale) {
    }
}
