package oplus.content.res;

public class OplusExtraConfiguration {
    public static final int ACESSIBLE_OPLUS_MODE_CHANGED = 67108864;
    public static final int CONFIG_FLIPFONT = 33554432;
    public static final int FONT_VARIATION_SETTINGS_CHANGED = 16777216;
    public static final long MAX_MATERIAL_HIGH = 1879048191L;
    public static final int OPLUS_CONFIG_CHANGED = 268435456;
    public static final int OPLUS_CONFIG_FOLER_ANGLE = 65536;
    public static final int OPLUS_DARKMODE_RANK_CHANGED = 1;
    public static final int THEME_NEW_SKIN_CHANGED = 150994944;
    public static final int THEME_OLD_SKIN_CHANGED = 134217728;
    public static final int UX_ICON_CONFIG_CHANGED = 8388608;
    public static final int WINDOW_CONFIG_EXTRA_FLAG = 262144;
    public static final int WINDOW_CONFIG_SCENARIO = 524288;

    public int mFont = 0;
    public int mFlipFont = 0;
    public int mThemeChanged = 0;
    public long mThemeChangedFlags = 0L;
    public int mAccessibleChanged = 0;
    public long mUxIconConfig = 0L;
    public int mUserId = -1;
    public int mFontUserId = -1;
    public long mMaterialColor = -1L;
    public int mFontVariationSettings = -1;
    public String mIconPackName = "";
    public float mDarkModeDialogBgMaxL = -1.0f;
    public float mDarkModeBackgroundMaxL = -1.0f;
    public float mDarkModeForegroundMinL = -1.0f;
    public int mFontOpSansSettings = -1;
    public float mFoldingAngle = -1.0f;
    public String mThemePrefix = "";
    public int mBurmeseFontFlag = -1;
    public long mOplusChangedConfigs = 0L;
    public long mOplusConfigType = 0L;

    public OplusExtraConfiguration() {}
}
