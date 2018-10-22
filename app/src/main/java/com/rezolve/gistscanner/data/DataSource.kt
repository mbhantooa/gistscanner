package com.rezolve.gistscanner.data

import com.rezolve.gistscanner.data.retrofit.RetrofitService
import com.rezolve.gistscanner.data.retrofit.createService
import com.rezolve.gistscanner.model.CommentRequest
import com.rezolve.gistscanner.model.GistComment
import org.jetbrains.anko.AnkoLogger
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
                           callback: NetworkCallback<List<GistComment>>)

    fun createGistComment(gistId: String, username: String, password: String, comment: String,
                          callback: NetworkCallback<GistComment>)
}

@Singleton
internal class RetrofitGistDataSource @Inject constructor() : IGistDataSource, AnkoLogger {
    override fun getGistCommentList(gistId: String,
                                    username: String,
                                    password: String,
                                    callback: NetworkCallback<List<GistComment>>) {
        val retrofitService = createService(
                RetrofitService::class.java, username, password)
        Network.request(retrofitService.getGistComments(gistId), callback)
    }

    override fun createGistComment(gistId: String,
                                   username: String,
                                   password: String,
                                   comment: String,
                                   callback: NetworkCallback<GistComment>) {
        val retrofitService = createService(RetrofitService::class.java, username, password)
        Network.request(retrofitService.createComment(gistId, CommentRequest(comment)), callback)
    }
}