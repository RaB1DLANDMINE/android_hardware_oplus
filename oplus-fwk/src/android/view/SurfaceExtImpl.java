package android.view;

/**
 * Oplus Surface extension used by stock Oplus apps.
 *
 * @hide
 */
public class SurfaceExtImpl implements ISurfaceExt {
    private final Object mLock;
    private final Surface mSurface;
    private long mNativeObject;

    static {
        System.loadLibrary("oplusgui_jni");
    }

    public SurfaceExtImpl(Object base) {
        mSurface = (Surface) base;
        mLock = mSurface.mLock;
    }

    @Override
    public native void nativeSetMaxDequeuedBufferCount(long nativeObject, int bufferCount);

    @Override
    public void setMaxDequeuedBufferCount(int bufferCount) {
        synchronized (mLock) {
            mNativeObject = mSurface.mNativeObject;
            if (mNativeObject != 0) {
                nativeSetMaxDequeuedBufferCount(mNativeObject, bufferCount);
            }
        }
    }
}
