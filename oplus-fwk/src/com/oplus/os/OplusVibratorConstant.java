package com.oplus.os;

/**
 * Stub for OEM linear-motor vibrator waveform constants. OplusCamera reads these amplitude/time
 * arrays to play rapid haptic patterns. Values are plausible placeholders (equal-length pairs);
 * exact OEM waveforms are non-critical for bring-up.
 */
public class OplusVibratorConstant {
    public static final int[] RAPID_MIDDLE_WAVEFORM_AMPLITUDE = {0, 180, 0};
    public static final long[] RAPID_MIDDLE_WAVEFORM_TIME = {0, 20, 20};
    public static final int[] RAPID_STRONG_WAVEFORM_AMPLITUDE = {0, 255, 0};
    public static final long[] RAPID_STRONG_WAVEFORM_TIME = {0, 20, 20};
}
