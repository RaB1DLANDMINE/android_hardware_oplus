package com.oplus.wrapper.app.contentsuggestions;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class ContentSuggestionsManager {
    public static final String EXTRA_BITMAP = "android.contentsuggestions.extra.BITMAP";

    private final android.app.contentsuggestions.ContentSuggestionsManager mContentSuggestionsManager;

    public interface ClassificationsCallback {
        void onContentClassificationsAvailable(int statusCode,
                List<ContentClassification> classifications);
    }

    public interface SelectionsCallback {
        void onContentSelectionsAvailable(int statusCode, List<ContentSelection> selections);
    }

    public ContentSuggestionsManager(Context context) {
        mContentSuggestionsManager = (android.app.contentsuggestions.ContentSuggestionsManager)
                context.getSystemService("content_suggestions");
    }

    public void provideContextImage(Bitmap bitmap, Bundle imageContextRequestExtras) {
        if (mContentSuggestionsManager != null) {
            mContentSuggestionsManager.provideContextImage(bitmap, imageContextRequestExtras);
        }
    }

    public void provideContextImage(int taskId, Bundle imageContextRequestExtras) {
        if (mContentSuggestionsManager != null) {
            mContentSuggestionsManager.provideContextImage(taskId, imageContextRequestExtras);
        }
    }

    public void suggestContentSelections(SelectionsRequest request, Executor callbackExecutor,
            SelectionsCallback callback) {
        if (mContentSuggestionsManager == null) {
            return;
        }
        mContentSuggestionsManager.suggestContentSelections(request.getSelectionsRequest(), callbackExecutor,
                (statusCode, selections) -> {
                    List<ContentSelection> oplusSelections = new ArrayList<>(selections.size());
                    for (android.app.contentsuggestions.ContentSelection selection : selections) {
                        oplusSelections.add(new ContentSelection(selection));
                    }
                    callback.onContentSelectionsAvailable(statusCode, oplusSelections);
                });
    }

    public void classifyContentSelections(ClassificationsRequest request, Executor callbackExecutor,
            ClassificationsCallback callback) {
        if (mContentSuggestionsManager == null) {
            return;
        }
        mContentSuggestionsManager.classifyContentSelections(request.getClassificationsRequest(),
                callbackExecutor, (statusCode, classifications) -> {
                    List<ContentClassification> oplusClassifications =
                            new ArrayList<>(classifications.size());
                    for (android.app.contentsuggestions.ContentClassification classification
                            : classifications) {
                        oplusClassifications.add(new ContentClassification(classification));
                    }
                    callback.onContentClassificationsAvailable(statusCode, oplusClassifications);
                });
    }

    public void notifyInteraction(String requestId, Bundle interaction) {
        if (mContentSuggestionsManager != null) {
            mContentSuggestionsManager.notifyInteraction(requestId, interaction);
        }
    }
}
