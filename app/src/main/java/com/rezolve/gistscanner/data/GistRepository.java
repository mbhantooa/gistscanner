package com.rezolve.gistscanner.data;

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

    public void fetchGistCommentList(String gistID, String username, String password,
                                     Callback<GistCommentListResponse> callback) {
        gistApi.getGistCommentList(gistID, username, password, callback);
    }

    public void createdGistComment(String gistID, String username,
                                   String password, String comment,
                                   Callback<CreateGistResponse> callback) {
        gistApi.createGistComment(gistID, username, password, comment, callback);
    }
}
