package com.oplus.app;

import android.app.ActivityManager;
import java.util.List;

public interface IOplusTaskInfoChangeListener {
    void onVisibleTasksInfoChange(List<ActivityManager.RunningTaskInfo> tasks);
}
