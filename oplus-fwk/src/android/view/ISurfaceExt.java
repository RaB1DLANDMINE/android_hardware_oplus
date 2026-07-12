package android.view;

/**
 * Compatibility interface used by Oplus framework callers.
 *
 * @hide
 */
public interface ISurfaceExt {
    default void setMaxDequeuedBufferCount(int bufferCount) {
    }

    default void nativeSetMaxDequeuedBufferCount(long nativeObject, int bufferCount) {
    }
}
