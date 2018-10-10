package com.rezolve.gistscanner.data.retrofit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rezolve.gistscanner.data.IGistDataSource;
import com.rezolve.gistscanner.model.CommentRequest;
import com.rezolve.gistscanner.model.CreateGistResponse;
import com.rezolve.gistscanner.model.GistComment;
import com.rezolve.gistscanner.model.GistCommentListResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Response;

@Singleton
public class RetrofitGistDataSource implements IGistDataSource {

    @Inject
    public RetrofitGistDataSource() {
    }

    @Override
    public void getGistCommentList(@NonNull String gistId,
                                   @NonNull String username, @NonNull String password,
                                   @Nullable Callback<GistCommentListResponse> callback) {

        RetrofitService retrofitService = Util.createService(
                RetrofitService.class, username, password
        );
        retrofitService.getGistComments(gistId)
                .enqueue(new retrofit2.Callback<List<GistComment>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<GistComment>> call,
                                           @NonNull Response<List<GistComment>> response) {
                        if (callback != null)
                            callback.onComplete(new GistCommentListResponse(
                                    response.body(),
                                    null
                            ));

                        // TODO: 10/10/2018 handle failure
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<GistComment>> call, @NonNull Throwable t) {
                        if (callback != null)
                            callback.onComplete(
                                    new GistCommentListResponse(null, t)
                            );
                    }
                });
    }

    @Override
    public void createGistComment(@NonNull String gistId, @NonNull String username,
                                  @NonNull String password, @NonNull String comment,
                                  @Nullable Callback<CreateGistResponse> callback) {
        RetrofitService retrofitService = Util.createService(
                RetrofitService.class, username, password
        );

        retrofitService.createComment(gistId, new CommentRequest(comment))
                .enqueue(new retrofit2.Callback<GistComment>() {
                    @Override
                    public void onResponse(@NonNull Call<GistComment> call, @NonNull Response<GistComment> response) {
                        if (null != callback)
                            callback.onComplete(new CreateGistResponse(response.body(), null));

                        // TODO: 10/10/2018 handle failure
                    }

                    @Override
                    public void onFailure(@NonNull Call<GistComment> call, @NonNull Throwable t) {
                        if (null != callback)
                            callback.onComplete(new CreateGistResponse(null, t));
                    }
                });
    }
}
