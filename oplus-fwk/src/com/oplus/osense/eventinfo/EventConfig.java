package com.oplus.osense.eventinfo;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventConfig implements Parcelable {
    public static final Parcelable.Creator<EventConfig> CREATOR =
            new Parcelable.Creator<EventConfig>() {
                @Override
                public EventConfig createFromParcel(Parcel in) {
                    return new EventConfig(in);
                }

                @Override
                public EventConfig[] newArray(int size) {
                    return new EventConfig[size];
                }
            };

    private Set<Integer> mEventSet;
    private Set<OsenseConfig> mOsenseConfigSet;

    public EventConfig(Parcel in) {
        ClassLoader loader = EventConfig.class.getClassLoader();
        List<Integer> eventSet = new ArrayList<>();
        in.readList(eventSet, loader);
        mEventSet = new HashSet<>();
        if (!eventSet.isEmpty()) {
            mEventSet.addAll(eventSet);
        }

        List<OsenseConfig> osenseConfigSet = new ArrayList<>();
        in.readList(osenseConfigSet, loader);
        mOsenseConfigSet = new HashSet<>();
        if (!osenseConfigSet.isEmpty()) {
            mOsenseConfigSet.addAll(osenseConfigSet);
        }
    }

    public EventConfig() {
        mEventSet = new HashSet<>();
        mOsenseConfigSet = new HashSet<>();
    }

    public EventConfig(HashSet<Integer> events) {
        mEventSet = new HashSet<>();
        if (events != null && !events.isEmpty()) {
            mEventSet.addAll(events);
        }
        mOsenseConfigSet = new HashSet<>();
    }

    public void setOsenseConfigSet(HashSet<OsenseConfig> osenseConfigSet) {
        getOsenseConfigSet().clear();
        if (osenseConfigSet != null) {
            mOsenseConfigSet.addAll(osenseConfigSet);
        }
    }

    public Set<Integer> getEventSet() {
        if (mEventSet == null) {
            mEventSet = new HashSet<>();
        }
        return mEventSet;
    }

    public Set<OsenseConfig> getOsenseConfigSet() {
        if (mOsenseConfigSet == null) {
            mOsenseConfigSet = new HashSet<>();
        }
        return mOsenseConfigSet;
    }

    public Set<Integer> getAllEventTypes() {
        Set<Integer> allEventTypes = new HashSet<>();
        if (mOsenseConfigSet != null && !mOsenseConfigSet.isEmpty()) {
            for (OsenseConfig osenseConfig : mOsenseConfigSet) {
                allEventTypes.add(osenseConfig.getEventType());
            }
        }
        if (mEventSet != null && !mEventSet.isEmpty()) {
            allEventTypes.addAll(mEventSet);
        }
        return allEventTypes;
    }

    public Set<OsenseConfig> getAllOsenseConfigs() {
        Set<OsenseConfig> osenseConfigs = new HashSet<>();
        if (mEventSet != null && !mEventSet.isEmpty()) {
            for (Integer eventType : mEventSet) {
                osenseConfigs.add(new OsenseConfig(eventType, null));
            }
        }
        if (mOsenseConfigSet != null && !mOsenseConfigSet.isEmpty()) {
            osenseConfigs.addAll(mOsenseConfigSet);
        }
        return osenseConfigs;
    }

    public void addEvent(int eventType) {
        getEventSet().add(eventType);
    }

    public void addOsenseConfig(OsenseConfig osenseConfig) {
        getOsenseConfigSet().add(osenseConfig);
    }

    @Override
    public String toString() {
        return "EventConfig{mEventSet=" + mEventSet + ", mOsenseConfigSet=" + mOsenseConfigSet
                + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mEventSet != null) {
            dest.writeList(new ArrayList<>(mEventSet));
        }
        if (mOsenseConfigSet != null) {
            dest.writeList(new ArrayList<>(mOsenseConfigSet));
        }
    }
}
