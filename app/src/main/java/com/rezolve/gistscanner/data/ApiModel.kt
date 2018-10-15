package com.rezolve.gistscanner.data

import com.rezolve.gistscanner.model.GistComment
import java.lang.IllegalArgumentException

internal open class GistApiResponse<R, E : Throwable>(val response: R?,
                                                      private val exception: E?) {
    init {
        if ((response == null) == (exception == null)) {
            throw IllegalArgumentException("At least one of the R or E should not be null.")
        }
    }

    fun isSuccessful(): Boolean {
        return response != null && exception == null
    }
}

internal class CreateGistResponse(response: GistComment?, exception: Throwable?)
    : GistApiResponse<GistComment, Throwable>(response, exception)

internal class GistCommentListResponse(response: List<GistComment>?, exception: Throwable?)
    : GistApiResponse<List<GistComment>, Throwable>(response, exception)