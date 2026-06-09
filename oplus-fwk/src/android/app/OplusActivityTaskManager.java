package android.app;

import android.content.ComponentName;
import android.os.RemoteException;
import android.view.SurfaceControl;
import com.oplus.app.OplusStartingWindowObserver;
import com.oplus.app.OplusTaskInfoChangeListener;
import java.util.ArrayList;
import java.util.List;

public class OplusActivityTaskManager {
    private static final OplusActivityTaskManager INSTANCE = new OplusActivityTaskManager();

    public static OplusActivityTaskManager getInstance() {
        return INSTANCE;
    }

    public ComponentName getTopActivityComponentName() throws RemoteException {
        return null;
    }

    public List<ActivityManager.RunningTaskInfo> getVisibleTasks(int displayId)
            throws RemoteException {
        return new ArrayList<>();
    }

    public boolean registerTaskInfoChangeListener(
            OplusTaskInfoChangeListener listener, int displayId, int flags)
            throws RemoteException {
        return true;
    }

    public boolean unregisterTaskInfoChangeListener(OplusTaskInfoChangeListener listener)
            throws RemoteException {
        return true;
    }

    public void registerStartingWindowObserver(OplusStartingWindowObserver observer) {
    }

    public void unregisterStartingWindowObserver(OplusStartingWindowObserver observer) {
    }

    public SurfaceControl getTaskSurface(int taskId) {
        return null;
    }
}
