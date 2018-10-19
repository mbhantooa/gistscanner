package com.rezolve.gistscanner.data;

import android.support.annotation.NonNull;

import com.rezolve.gistscanner.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GistRepository {
    private final IGistDataSource gistApi;

    @Inject
    public GistRepository(@Remote IGistDataSource gistApi) {
        this.gistApi = gistApi;
    }

    public void fetchGistCommentList(@NonNull String gistID, @NonNull String username, @NonNull String password,
                                     @NonNull Callback<GistCommentListResponse> callback) {
        gistApi.getGistCommentList(gistID, username, password, callback);
    }

    public void createdGistComment(@NonNull String gistID, @NonNull String username,
                                   @NonNull String password, @NonNull String comment,
                                   @NonNull Callback<CreateGistResponse> callback) {
        gistApi.createGistComment(gistID, username, password, comment, callback);
    }
}
