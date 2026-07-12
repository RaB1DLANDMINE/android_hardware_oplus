package com.oplus.wrapper.app.contentsuggestions;

import android.graphics.Point;
import android.os.Bundle;

public final class SelectionsRequest {
    private final android.app.contentsuggestions.SelectionsRequest mSelectionsRequest;

    private SelectionsRequest(android.app.contentsuggestions.SelectionsRequest selectionsRequest) {
        mSelectionsRequest = selectionsRequest;
    }

    public int getTaskId() {
        return mSelectionsRequest.getTaskId();
    }

    public Point getInterestPoint() {
        return mSelectionsRequest.getInterestPoint();
    }

    public Bundle getExtras() {
        return mSelectionsRequest.getExtras();
    }

    android.app.contentsuggestions.SelectionsRequest getSelectionsRequest() {
        return mSelectionsRequest;
    }

    public static final class Builder {
        private final android.app.contentsuggestions.SelectionsRequest.Builder mBuilder;

        public Builder(int taskId) {
            mBuilder = new android.app.contentsuggestions.SelectionsRequest.Builder(taskId);
        }

        public Builder setExtras(Bundle extras) {
            mBuilder.setExtras(extras);
            return this;
        }

        public Builder setInterestPoint(Point interestPoint) {
            mBuilder.setInterestPoint(interestPoint);
            return this;
        }

        public SelectionsRequest build() {
            return new SelectionsRequest(mBuilder.build());
        }
    }
}
