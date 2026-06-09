package com.oplus.inner.content.res;

import android.content.res.Configuration;
import android.content.res.OplusBaseConfiguration;
import java.lang.reflect.Method;
import oplus.content.res.OplusExtraConfiguration;

public class ConfigurationWrapper {
    public static void setFlipFont(Configuration configuration, int flipfont) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mFlipFont = flipfont;
        }
    }

    public static int getFlipFont(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mFlipFont : -1;
    }

    public static int getThemeChanged(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mThemeChanged : -1;
    }

    public static void setThemeChanged(Configuration configuration, int val) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mThemeChanged = val;
        }
    }

    public static long getThemeChangedFlags(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mThemeChangedFlags : -1L;
    }

    public static void setThemeChangedFlags(Configuration configuration, long val) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mThemeChangedFlags = val;
        }
    }

    public static int getAccessibleChanged(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mAccessibleChanged : 0;
    }

    public static void setAccessibleChanged(Configuration configuration, int val) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mAccessibleChanged = val;
        }
    }

    public static long getUxIconConfig(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mUxIconConfig : -1L;
    }

    public static void setUxIconConfig(Configuration configuration, long val) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mUxIconConfig = val;
        }
    }

    public static long getMaterialColor(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mMaterialColor : -1L;
    }

    public static void setMaterialColor(Configuration configuration, long val) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mMaterialColor = val;
        }
    }

    public static String getIconPackName(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mIconPackName : "";
    }

    public static void setIconPackName(Configuration configuration, String val) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mIconPackName = val;
        }
    }

    public static String getCustomThemePath(Configuration configuration) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        return extra != null ? extra.mThemePrefix : "";
    }

    public static void setCustomThemePath(Configuration configuration, String val) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mThemePrefix = val;
        }
    }

    public static void setDarkModeStyleArgs(Configuration configuration, float bgMaxL, float fgMaxL, float dialogBgMaxL) {
        OplusExtraConfiguration extra = getExtraConfiguration(configuration);
        if (extra != null) {
            extra.mDarkModeBackgroundMaxL = bgMaxL;
            extra.mDarkModeForegroundMinL = fgMaxL;
            extra.mDarkModeDialogBgMaxL = dialogBgMaxL;
        }
    }

    public static boolean isDarkModeIconOpen(Configuration configuration) {
        return ((getUxIconConfig(configuration) >> 61) & 1L) == 1L;
    }

    public static void toggleDarkModeIconConfig(Configuration configuration) {
        setUxIconConfig(configuration, getUxIconConfig(configuration) ^ (1L << 61));
    }

    protected static OplusExtraConfiguration getExtraConfiguration(Configuration configuration) {
        OplusBaseConfiguration baseConfiguration = typeCasting(configuration);
        if (baseConfiguration != null) {
            return baseConfiguration.mOplusExtraConfiguration;
        }
        return null;
    }

    private static OplusBaseConfiguration typeCasting(Configuration configuration) {
        try {
            Class<?> helper = Class.forName("com.oplus.util.OplusTypeCastingHelper");
            Method method = helper.getMethod("typeCasting", Class.class, Object.class);
            return (OplusBaseConfiguration) method.invoke(null, OplusBaseConfiguration.class, configuration);
        } catch (Throwable ignored) {
            return null;
        }
    }
}
