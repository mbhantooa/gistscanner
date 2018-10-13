package com.rezolve.gistscanner.data.retrofit;

import com.rezolve.gistscanner.model.CommentRequest;
import com.rezolve.gistscanner.model.GistComment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("gists/{gistID}/comments")
    Call<List<GistComment>> getGistComments(@Path("gistID") String gistID);

    @POST("gists/{gistID}/comments")
    Call<GistComment> createComment(@Path("gistID") String gistID, @Body CommentRequest commentRequest);
}
