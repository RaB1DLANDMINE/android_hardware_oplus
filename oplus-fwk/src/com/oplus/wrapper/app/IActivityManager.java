package com.oplus.wrapper.app;

import android.content.res.Configuration;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

public interface IActivityManager extends android.os.IInterface {
    boolean clearApplicationUserData(
            String packageName,
            boolean keepState,
            com.oplus.wrapper.content.pm.IPackageDataObserver observer,
            int userId)
            throws RemoteException;

    void closeSystemDialogs(String reason) throws RemoteException;

    Configuration getConfiguration() throws RemoteException;

    com.oplus.wrapper.content.pm.UserInfo getCurrentUser() throws RemoteException;

    String getLaunchedFromPackage(IBinder activityToken) throws RemoteException;

    long[] getProcessPss(int[] pids) throws RemoteException;

    int[] getRunningUserIds() throws RemoteException;

    boolean isInLockTaskMode() throws RemoteException;

    void registerProcessObserver(IProcessObserver observer) throws RemoteException;

    void registerUserSwitchObserver(IUserSwitchObserver observer, String name) throws RemoteException;

    boolean removeTask(int taskId) throws RemoteException;

    void resumeAppSwitches() throws RemoteException;

    void setProcessLimit(int max) throws RemoteException;

    boolean switchUser(int userId) throws RemoteException;

    void unregisterProcessObserver(IProcessObserver observer) throws RemoteException;

    void unregisterUserSwitchObserver(IUserSwitchObserver observer) throws RemoteException;

    boolean updateConfiguration(Configuration values) throws RemoteException;

    abstract class Stub implements IActivityManager {
        public static IActivityManager asInterface(IBinder obj) {
            android.app.IActivityManager target = android.app.IActivityManager.Stub.asInterface(obj);
            return target == null ? null : new Proxy(target);
        }

        private static final class Proxy implements IActivityManager {
            private final android.app.IActivityManager target;
            private final Map<IProcessObserver, android.app.IProcessObserver> processObservers =
                    new HashMap<>();
            private final Map<IUserSwitchObserver, android.app.IUserSwitchObserver> userSwitchObservers =
                    new HashMap<>();

            Proxy(android.app.IActivityManager target) {
                this.target = target;
            }

            @Override
            public IBinder asBinder() {
                return target.asBinder();
            }

            @Override
            public boolean clearApplicationUserData(
                    String packageName,
                    boolean keepState,
                    com.oplus.wrapper.content.pm.IPackageDataObserver observer,
                    int userId)
                    throws RemoteException {
                android.content.pm.IPackageDataObserver platformObserver = null;
                if (observer != null) {
                    platformObserver =
                            new android.content.pm.IPackageDataObserver.Stub() {
                                @Override
                                public void onRemoveCompleted(String packageName, boolean succeeded)
                                        throws RemoteException {
                                    observer.onRemoveCompleted(packageName, succeeded);
                                }
                            };
                }
                return target.clearApplicationUserData(
                        packageName, keepState, platformObserver, userId);
            }

            @Override
            public void closeSystemDialogs(String reason) throws RemoteException {
                target.closeSystemDialogs(reason);
            }

            @Override
            public Configuration getConfiguration() throws RemoteException {
                return target.getConfiguration();
            }

            @Override
            public com.oplus.wrapper.content.pm.UserInfo getCurrentUser() throws RemoteException {
                android.content.pm.UserInfo userInfo = target.getCurrentUser();
                return userInfo == null ? null : new com.oplus.wrapper.content.pm.UserInfo(userInfo);
            }

            @Override
            public String getLaunchedFromPackage(IBinder activityToken) throws RemoteException {
                return target.getLaunchedFromPackage(activityToken);
            }

            @Override
            public long[] getProcessPss(int[] pids) throws RemoteException {
                return target.getProcessPss(pids);
            }

            @Override
            public int[] getRunningUserIds() throws RemoteException {
                return target.getRunningUserIds();
            }

            @Override
            public boolean isInLockTaskMode() throws RemoteException {
                return target.isInLockTaskMode();
            }

            @Override
            public void registerProcessObserver(IProcessObserver observer) throws RemoteException {
                if (observer == null) {
                    return;
                }
                android.app.IProcessObserver platformObserver =
                        processObservers.computeIfAbsent(observer, Proxy::toPlatformObserver);
                target.registerProcessObserver(platformObserver);
            }

            @Override
            public void registerUserSwitchObserver(IUserSwitchObserver observer, String name)
                    throws RemoteException {
                if (observer == null) {
                    return;
                }
                android.app.IUserSwitchObserver platformObserver =
                        userSwitchObservers.computeIfAbsent(observer, Proxy::toPlatformObserver);
                target.registerUserSwitchObserver(platformObserver, name);
            }

            @Override
            public boolean removeTask(int taskId) throws RemoteException {
                return target.removeTask(taskId);
            }

            @Override
            public void resumeAppSwitches() throws RemoteException {
                target.resumeAppSwitches();
            }

            @Override
            public void setProcessLimit(int max) throws RemoteException {
                target.setProcessLimit(max);
            }

            @Override
            public boolean switchUser(int userId) throws RemoteException {
                return target.switchUser(userId);
            }

            @Override
            public void unregisterProcessObserver(IProcessObserver observer) throws RemoteException {
                android.app.IProcessObserver platformObserver = processObservers.remove(observer);
                if (platformObserver != null) {
                    target.unregisterProcessObserver(platformObserver);
                }
            }

            @Override
            public void unregisterUserSwitchObserver(IUserSwitchObserver observer)
                    throws RemoteException {
                android.app.IUserSwitchObserver platformObserver = userSwitchObservers.remove(observer);
                if (platformObserver != null) {
                    target.unregisterUserSwitchObserver(platformObserver);
                }
            }

            @Override
            public boolean updateConfiguration(Configuration values) throws RemoteException {
                return target.updateConfiguration(values);
            }

            private static android.app.IProcessObserver toPlatformObserver(
                    IProcessObserver observer) {
                return new android.app.IProcessObserver.Stub() {
                    @Override
                    public void onForegroundActivitiesChanged(
                            int pid, int uid, boolean foregroundActivities)
                            throws RemoteException {
                        observer.onForegroundActivitiesChanged(pid, uid, foregroundActivities);
                    }

                    @Override
                    public void onForegroundServicesChanged(int pid, int uid, int serviceTypes)
                            throws RemoteException {
                        observer.onForegroundServicesChanged(pid, uid, serviceTypes);
                    }

                    @Override
                    public void onProcessDied(int pid, int uid) throws RemoteException {
                        observer.onProcessDied(pid, uid);
                    }

                    @Override
                    public void onProcessStarted(
                            int pid,
                            int processUid,
                            int packageUid,
                            String packageName,
                            String processName) {
                    }
                };
            }

            private static android.app.IUserSwitchObserver toPlatformObserver(
                    IUserSwitchObserver observer) {
                return new android.app.IUserSwitchObserver.Stub() {
                    @Override
                    public void onBeforeUserSwitching(
                            int newUserId, android.os.IRemoteCallback reply)
                            throws RemoteException {
                        observer.onBeforeUserSwitching(
                                newUserId, com.oplus.wrapper.os.IRemoteCallback.Stub.asInterface(reply));
                    }

                    @Override
                    public void onForegroundProfileSwitch(int newProfileId)
                            throws RemoteException {
                        observer.onForegroundProfileSwitch(newProfileId);
                    }

                    @Override
                    public void onLockedBootComplete(int newUserId) throws RemoteException {
                        observer.onLockedBootComplete(newUserId);
                    }

                    @Override
                    public void onUserSwitchComplete(int newUserId) throws RemoteException {
                        observer.onUserSwitchComplete(newUserId);
                    }

                    @Override
                    public void onUserSwitching(int newUserId, android.os.IRemoteCallback reply)
                            throws RemoteException {
                        observer.onUserSwitching(
                                newUserId, com.oplus.wrapper.os.IRemoteCallback.Stub.asInterface(reply));
                    }
                };
            }
        }
    }
}
