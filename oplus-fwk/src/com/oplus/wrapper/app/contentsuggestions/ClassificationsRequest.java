package com.oplus.wrapper.app.contentsuggestions;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public final class ClassificationsRequest {
    private final android.app.contentsuggestions.ClassificationsRequest mClassificationsRequest;

    private ClassificationsRequest(android.app.contentsuggestions.ClassificationsRequest classificationsRequest) {
        mClassificationsRequest = classificationsRequest;
    }

    public List<ContentSelection> getSelections() {
        List<android.app.contentsuggestions.ContentSelection> selections =
                mClassificationsRequest.getSelections();
        List<ContentSelection> oplusSelections = new ArrayList<>(selections.size());
        for (android.app.contentsuggestions.ContentSelection selection : selections) {
            oplusSelections.add(new ContentSelection(selection));
        }
        return oplusSelections;
    }

    public Bundle getExtras() {
        return mClassificationsRequest.getExtras();
    }

    android.app.contentsuggestions.ClassificationsRequest getClassificationsRequest() {
        return mClassificationsRequest;
    }

    public static final class Builder {
        private final android.app.contentsuggestions.ClassificationsRequest.Builder mBuilder;

        public Builder(List<ContentSelection> selections) {
            List<android.app.contentsuggestions.ContentSelection> androidSelections =
                    new ArrayList<>(selections.size());
            for (ContentSelection selection : selections) {
                androidSelections.add(selection.getContentSelection());
            }
            mBuilder = new android.app.contentsuggestions.ClassificationsRequest.Builder(androidSelections);
        }

        public Builder setExtras(Bundle extras) {
            mBuilder.setExtras(extras);
            return this;
        }

        public ClassificationsRequest build() {
            return new ClassificationsRequest(mBuilder.build());
        }
    }
}
