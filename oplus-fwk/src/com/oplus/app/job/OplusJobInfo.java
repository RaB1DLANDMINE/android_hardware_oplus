package com.oplus.app.job;

import android.app.job.JobInfo;

public class OplusJobInfo {
    public static final int PRIORITY_BACKGROUND = 0;
    public static final int PRIORITY_DEFAULT = 1;
    public static final int PRIORITY_USER_INITIALED = 2;
    public static final int WORK_TYPE_CPU = 1;
    public static final int WORK_TYPE_IO = 2;
    public static final int WORK_TYPE_NETWORK = 8;
    public static final int WORK_TYPE_NONE = 0;
    public static final int WORK_TYPE_RAM = 4;
    public static final int PROTECT_FORE_FRAME = 0;
    public static final int PROTECT_FORE_NET = 1;
    public static final int SCENE_MODE_VIDEO = 1;
    public static final int SCENE_MODE_VIDEO_CALL = 2;
    public static final int SCENE_MODE_GAME = 4;

    private OplusJobInfo() {
    }

    public static final class Builder {
        private final JobInfo.Builder mBuilder;
        private boolean mRequiresBattIdle;
        private boolean mRequiresChargingRestriction;
        private boolean mExtraJob;
        private boolean mRequiresProtectFore;
        private boolean mHasCpuConstraint;
        private boolean mHasTemperatureConstraint;
        private boolean mRequiresProtectScene;
        private String mExtraStr;

        public Builder(JobInfo.Builder builder) {
            mBuilder = builder;
        }

        public void setRequiresBattIdle(boolean requiresBattIdle) {
            mRequiresBattIdle = requiresBattIdle;
        }

        public boolean getRequiresBattIdle() {
            return mRequiresBattIdle;
        }

        public void setRequiresChargingRestriction(boolean requiresChargingRestriction) {
            mRequiresChargingRestriction = requiresChargingRestriction;
        }

        public void setExtraJob(boolean isExtraJob) {
            mExtraJob = isExtraJob;
        }

        public boolean isExtraJob() {
            return mExtraJob;
        }

        public void setRequiresProtectFore(boolean requiresProtectFore) {
            mRequiresProtectFore = requiresProtectFore;
        }

        public void setRequiresProtectFore(boolean requiresProtectFore, int protectForeType) {
            mRequiresProtectFore = requiresProtectFore;
        }

        public boolean getRequiresProtectFore() {
            return mRequiresProtectFore;
        }

        public void setHasCpuConstraint(boolean hasCpuConstraint) {
            mHasCpuConstraint = hasCpuConstraint;
        }

        public boolean getHasCpuConstraint() {
            return mHasCpuConstraint;
        }

        public void setExtraStr(String str) {
            mExtraStr = str;
        }

        public String getExtraStr() {
            return mExtraStr;
        }

        public void setHasTemperatureConstraint(boolean hasTemperatureConstraint) {
            mHasTemperatureConstraint = hasTemperatureConstraint;
        }

        public boolean getHasTemperatureConstraint() {
            return mHasTemperatureConstraint;
        }

        public void setRequiresProtectScene(boolean requiresProtectScene, int protectScene) {
            mRequiresProtectScene = requiresProtectScene;
        }

        public boolean getRequiresProtectScene() {
            return mRequiresProtectScene;
        }

        public void setPriority(int priority) {
        }

        public void setWorkType(int workType) {
        }

        public JobInfo.Builder getBuilder() {
            return mBuilder;
        }
    }
}
