package com.rezolve.gistscanner.data.retrofit;

import android.support.annotation.NonNull;

import com.rezolve.gistscanner.data.Callback;
import com.rezolve.gistscanner.data.CreateGistResponse;
import com.rezolve.gistscanner.data.GistCommentListResponse;
import com.rezolve.gistscanner.data.IGistDataSource;
import com.rezolve.gistscanner.model.CommentRequest;
import com.rezolve.gistscanner.model.GistComment;

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
                                   @NonNull Callback<GistCommentListResponse> callback) {

        RetrofitService retrofitService = Util.createService(
                RetrofitService.class, username, password
        );
        retrofitService.getGistComments(gistId)
                .enqueue(new retrofit2.Callback<List<GistComment>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<GistComment>> call,
                                           @NonNull Response<List<GistComment>> response) {
                        if (response.isSuccessful()) {
                            callback.onComplete(new GistCommentListResponse(
                                    response.body(),
                                    null
                            ));
                        } else
                            callback.onComplete(new GistCommentListResponse(
                                    null, new Exception()));


                    }

                    @Override
                    public void onFailure(@NonNull Call<List<GistComment>> call, @NonNull Throwable t) {
                        callback.onComplete(
                                new GistCommentListResponse(null, t)
                        );
                    }
                });
    }

    @Override
    public void createGistComment(@NonNull String gistId, @NonNull String username,
                                  @NonNull String password, @NonNull String comment,
                                  @NonNull Callback<CreateGistResponse> callback) {
        RetrofitService retrofitService = Util.createService(
                RetrofitService.class, username, password
        );

        retrofitService.createComment(gistId, new CommentRequest(comment))
                .enqueue(new retrofit2.Callback<GistComment>() {
                    @Override
                    public void onResponse(@NonNull Call<GistComment> call, @NonNull Response<GistComment> response) {
                        if (response.isSuccessful())
                            callback.onComplete(new CreateGistResponse(response.body(), null));
                        else
                            callback.onComplete(new CreateGistResponse(null, new Exception()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<GistComment> call, @NonNull Throwable t) {
                        callback.onComplete(new CreateGistResponse(null, t));
                    }
                });
    }
}
