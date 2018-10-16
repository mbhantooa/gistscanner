package com.rezolve.gistscanner.data

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