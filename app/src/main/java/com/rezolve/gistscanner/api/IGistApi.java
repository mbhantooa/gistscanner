package com.rezolve.gistscanner.api;

import android.support.annotation.NonNull;

import com.rezolve.gistscanner.model.CreateGistResponse;
import com.rezolve.gistscanner.model.GistCommentListResponse;

/**
 * Represents the contract that is needed for the different service calls for our purpose.
 * <p>
 * The user should be able to see a list of comments on a given gists and thus add another one
 * with some given context.
 * <p>
 * A {@link Callback} is set to get updates on the specific operations. Note that error handling
 * is done through the response type itself as it wraps both a potential response and also the
 * possible exception.
 */
public interface IGistApi {

    void setCallback(Callback callback);

    void getGistCommentList(@NonNull String gistId,
                            @NonNull String username,
                            @NonNull String password);

    void createGistComment(@NonNull String gistId,
                           @NonNull String username,
                           @NonNull String password,
                           @NonNull String comment);

    interface Callback {
        void onGistCommentListComplete(@NonNull GistCommentListResponse gistCommentListResponse);

        void onGistCommentCreated(CreateGistResponse createGistResponse);
    }
}
