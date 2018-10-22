package com.rezolve.gistscanner.data

import com.rezolve.gistscanner.data.retrofit.RetrofitService
import com.rezolve.gistscanner.data.retrofit.createService
import com.rezolve.gistscanner.model.CommentRequest
import com.rezolve.gistscanner.model.GistComment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

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
internal interface IGistDataSource {
    fun getGistCommentList(gistId: String, username: String, password: String,
                           callback: Callback<GistCommentListResponse>)

    fun createGistComment(gistId: String, username: String, password: String, comment: String,
                          callback: Callback<CreateGistResponse>)
}

internal interface Callback<T> {
    fun onComplete(response: T)
}

@Singleton
internal class RetrofitGistDataSource @Inject constructor() : IGistDataSource, AnkoLogger {
    override fun getGistCommentList(gistId: String,
                                    username: String,
                                    password: String, callback:
                                    Callback<GistCommentListResponse>) {
        val retrofitService = createService(
                RetrofitService::class.java, username, password)
        retrofitService.getGistComments(gistId)
                .enqueue(object : retrofit2.Callback<List<GistComment>> {
                    override fun onResponse(call: Call<List<GistComment>>,
                                            response: Response<List<GistComment>>) {
                        debug("List comment response $response")
                        callback.let {
                            if (response.isSuccessful) it.onComplete(GistCommentListResponse(
                                    response.body()))
                            else it.onComplete(GistCommentListResponse(null, Exception()))
                        }
                    }

                    override fun onFailure(call: Call<List<GistComment>>, t: Throwable) {
                        callback.onComplete(
                                GistCommentListResponse(null, t)
                        )
                    }
                })

    }

    override fun createGistComment(gistId: String,
                                   username: String,
                                   password: String,
                                   comment: String,
                                   callback: Callback<CreateGistResponse>) {
        val retrofitService = createService(RetrofitService::class.java, username, password)
        retrofitService.createComment(gistId, CommentRequest(comment))
                .enqueue(object : retrofit2.Callback<GistComment> {
                    override fun onResponse(call: Call<GistComment>, response: Response<GistComment>) {
                        callback.let {
                            if (response.isSuccessful) it.onComplete(CreateGistResponse(response.body()))
                            else it.onComplete(CreateGistResponse(null, Exception()))
                        }
                    }

                    override fun onFailure(call: Call<GistComment>, t: Throwable) {
                        callback.onComplete(CreateGistResponse(null, t))
                    }

                })
    }
}