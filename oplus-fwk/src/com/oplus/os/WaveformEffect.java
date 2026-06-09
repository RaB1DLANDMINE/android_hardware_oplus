package com.oplus.os;

public class WaveformEffect {
    private int mEffectStrength;
    private int mEffectType;
    private boolean mAsynchronous;
    private boolean mStrengthSettingEnabled;

    public int getEffectStrength() {
        return mEffectStrength;
    }

    public int getEffectType() {
        return mEffectType;
    }

    public boolean isAsynchronous() {
        return mAsynchronous;
    }

    public boolean isStrengthSettingEnabled() {
        return mStrengthSettingEnabled;
    }

    public static class Builder {
        private final WaveformEffect mEffect = new WaveformEffect();

        public Builder setAsynchronous(boolean asynchronous) {
            mEffect.mAsynchronous = asynchronous;
            return this;
        }

        public Builder setEffectStrength(int strength) {
            mEffect.mEffectStrength = strength;
            return this;
        }

        public Builder setEffectType(int type) {
            mEffect.mEffectType = type;
            return this;
        }

        public Builder setStrengthSettingEnabled(boolean enabled) {
            mEffect.mStrengthSettingEnabled = enabled;
            return this;
        }

        public WaveformEffect build() {
            return mEffect;
        }
    }
}
