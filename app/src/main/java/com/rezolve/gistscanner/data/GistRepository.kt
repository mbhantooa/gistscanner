package com.rezolve.gistscanner.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class GistRepository @Inject constructor(private val gistApi: RetrofitGistDataSource) {
    fun fetchGistCommentList(gistID: String, username: String, password: String,
                             callback: Callback<GistCommentListResponse>) =
            gistApi.getGistCommentList(gistID, username, password, callback)

    fun createdGistComment(gistID: String, username: String, password: String,
                           comment: String,
                           callback: Callback<CreateGistResponse>) =
            gistApi.createGistComment(gistID, username, password, comment,
                    callback)
}