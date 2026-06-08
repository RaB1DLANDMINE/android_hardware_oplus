package com.oplus.flexiblewindow;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.concurrent.Executor;

public class FlexibleTaskView extends SurfaceView {
    public static final int CONTAINER_TYPE_OPLUS_NORMAL_APP_FOR_VIEW = 1;
    public static final String KEY_ALLOW_TASK_DETACH_FROM_EMBEDDING = "allow_task_detach_from_embedding";
    public static final String KEY_BACKGROUND_COLOR = "background_color";
    public static final String KEY_CONTAINER_TYPE = "key_container_type";
    public static final String KEY_CORNE_RADIUS = "cornerRadius";
    public static final String KEY_DRAW_BACKGROUND_COLOR = "draw_background_color";
    public static final String KEY_EMBEDDED_IN_LAUNCHER = "EMBEDDED_IN_LAUNCHER";
    public static final String KEY_FLEXIBLE_EMBEDDING = "flexible_embedding";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_INTERCEPT_BACK_KEY = "key_intercept_back_key";
    public static final String KEY_INTERCEPT_INPUT_EVENT = "intercept_input_event";
    public static final String KEY_LAUNCH_BOUNDS = "launchBounds";
    public static final String KEY_NEED_ROTATE_TASK_LEASH = "need_rotate_task_leash";
    public static final String KEY_NOT_REUSE_TASK = "NOT_REUSE_TASK";
    public static final String KEY_REPARENT_ALIGN = "reparent_align";
    public static final String KEY_SCENARIO = "scenario";
    public static final String KEY_SHADOW_RADIUS = "shadowRadius";
    public static final String KEY_SHOW_SURFACE_CORNER_RADIUS = "show_surface_corner_radius";
    public static final String KEY_SKIP_DELIVER_TO_CURRENT_TOP = "SKIP_DELIVER_TO_CURRENT_TOP";
    public static final String KEY_SUPER_LOCK = "super_locked";
    public static final String KEY_TASK_ID = "taskId";
    public static final String KEY_USER_BACKGROUND_COLOR = "use_default_background_color";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_USE_VIEW_SNAPSHOT = "use_view_snapshot";
    public static final String KEY_ZORDER_ON_TOP = "zorder_on_top";
    public static final int REPARENT_ALIGN_CENTER = 2;
    public static final int REPARENT_ALIGN_START = 1;

    public interface Listener {
        default void onInitialized(boolean isStartSuccess) {
        }

        default void onReleased() {
        }

        default void onTaskCreated(int taskId, ComponentName name) {
        }

        default void onTaskChanged(int taskId, ComponentName name, Rect rect) {
        }

        default void onTaskVisibilityChanged(int taskId, boolean visible) {
        }

        default void onTaskRemovalStarted(int taskId) {
        }

        default void onBackPressedOnTaskRoot(int taskId) {
        }

        default void updateTouchRegion(Region region) {
        }

        default void onTaskReplaced(int taskId, Intent intent, int userId) {
        }
    }

    public FlexibleTaskView(Context context) {
        super(context);
    }

    public FlexibleTaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleTaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Bundle bundle) {
    }

    public void init(int scenario, float cornerRadius, float shadowRadius, Rect launchBounds, Intent intent, int userId) {
    }

    public void init(int scenario, float cornerRadius, float shadowRadius, Rect launchBounds, int taskId) {
    }

    public void setShadowRadius(float shadowRadius) {
    }

    public void setListener(Executor executor, Listener listener) {
    }

    public void setWorkExecutor(Executor workExecutor) {
    }

    public Rect getLaunchBounds() {
        return null;
    }

    public void resize(Rect bounds) {
    }

    public void release() {
    }
}
