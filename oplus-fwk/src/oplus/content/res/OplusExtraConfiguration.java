package oplus.content.res;

public class OplusExtraConfiguration {
    public int mFlipFont = -1;
    public int mFontVariationSettings = -1;
    public int mThemeChanged = 0;
    public int mAccessibleChanged = 0;
    public long mMaterialColor = 0L;
    public long mThemeChangedFlags = 0L;
    public int mUserId = 0;
    public long mUxIconConfig = 0L;
    public String mIconPackName = "";
    public String mThemePrefix = "";
    public int mOplusConfigType = 0;
    public float mDarkModeBackgroundMaxL = -1.0f;
    public float mDarkModeDialogBgMaxL = -1.0f;
    public float mDarkModeForegroundMinL = -1.0f;

    public int getScenario() {
        return 0;
    }

    public int getExtraFlag() {
        return 0;
    }

    public int getFlag() {
        return 0;
    }

    public boolean isPuttDisplay() {
        return false;
    }
}
