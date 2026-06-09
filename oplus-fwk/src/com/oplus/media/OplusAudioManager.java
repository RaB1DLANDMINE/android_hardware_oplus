package com.oplus.media;

import android.media.IOplusAudioManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Singleton;
import android.util.Slog;

public class OplusAudioManager {
    private static final String TAG = "OplusAudioManager";

    private static final Singleton<IOplusAudioManager> INSTANCE =
            new Singleton<IOplusAudioManager>() {
                @Override
                protected IOplusAudioManager create() {
                    IBinder binder = ServiceManager.getService("audio");
                    if (binder == null) {
                        return null;
                    }

                    try {
                        return IOplusAudioManager.Stub.asInterface(binder.getExtension());
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            };

    private OplusAudioManager() {
    }

    private static final class InstanceHolder {
        private static final OplusAudioManager INSTANCE = new OplusAudioManager();

        private InstanceHolder() {
        }
    }

    public static OplusAudioManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static IOplusAudioManager getService() {
        return INSTANCE.get();
    }

    public void setRingerModeInternal(int ringerMode) {
        IOplusAudioManager service = getService();
        if (service == null) {
            Slog.w(TAG, "setRingerModeInternal failed because service has not been created");
            return;
        }

        try {
            service.setRingerModeInternal(ringerMode);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
