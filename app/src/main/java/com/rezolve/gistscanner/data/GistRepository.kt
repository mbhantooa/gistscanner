package com.rezolve.gistscanner.data

import com.rezolve.gistscanner.model.GistComment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GistRepository @Inject constructor(private val gistApi: RetrofitGistDataSource) {
    fun fetchGistCommentList(gistID: String, username: String, password: String,
                             callback: NetworkCallback<List<GistComment>>) =
            gistApi.getGistCommentList(gistID, username, password, callback)

    fun createdGistComment(gistID: String, username: String, password: String,
                           comment: String,
                           callback: NetworkCallback<GistComment>) =
            gistApi.createGistComment(gistID, username, password, comment,
                    callback)
}