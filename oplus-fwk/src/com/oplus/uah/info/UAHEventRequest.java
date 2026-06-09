package com.oplus.uah.info;

import java.util.ArrayList;

public class UAHEventRequest {
    public final int eventId;
    public final String scene;
    public final int timeout;
    public final ArrayList<?> resources;

    public UAHEventRequest(int eventId, String scene, int timeout, ArrayList<?> resources) {
        this.eventId = eventId;
        this.scene = scene;
        this.timeout = timeout;
        this.resources = resources;
    }
}
