package com.rezolve.gistscanner.data;

import android.support.annotation.NonNull;

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
public interface IGistDataSource {

    void getGistCommentList(@NonNull String gistId,
                            @NonNull String username,
                            @NonNull String password,
                            @NonNull Callback<GistCommentListResponse> callback);

    void createGistComment(@NonNull String gistId,
                           @NonNull String username,
                           @NonNull String password,
                           @NonNull String comment,
                           @NonNull Callback<CreateGistResponse> callback);

    @FunctionalInterface
    interface Callback<T> {
        void onComplete(@NonNull T response);
    }
}
