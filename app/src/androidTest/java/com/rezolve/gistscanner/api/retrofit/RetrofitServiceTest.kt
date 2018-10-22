package com.rezolve.gistscanner.api.retrofit

import com.rezolve.gistscanner.data.Network
import com.rezolve.gistscanner.data.NetworkCallback
import com.rezolve.gistscanner.data.retrofit.RetrofitService
import com.rezolve.gistscanner.data.retrofit.createService
import com.rezolve.gistscanner.model.CommentRequest
import com.rezolve.gistscanner.model.GistComment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RetrofitServiceTest {

    private val gistID = "92c0e856c23d0c8c6c26611028a32089"

    private val retrofitService = createService(
            RetrofitService::class.java, "mbhantooa", "P@ranoid2018"
    )

    @Test
    fun gist_comment_list_first_item() {
        val commentListCall = retrofitService.getGistComments(gistID)
        Network.request(commentListCall, NetworkCallback<List<GistComment>>()
                .apply {
                    success = {
                        assertFalse(it.isEmpty())
                        val firstGistComment = it[0]

                        assertNotNull(firstGistComment)
                        assertEquals("This is a small wrapper class that uses generics to" +
                                " callers determine the type of the actual response and the exception cases.",
                                firstGistComment.body)

                        assertEquals("mbhantooa", firstGistComment.user.login)
                    }

                    error = {
                        throw it
                    }
                })
    }

    @Test
    fun gist_create_comment() {
        val comment = "Comment added through testing"
        val commentRequest = CommentRequest(comment)
        val commentRequestCall = retrofitService.createComment(gistID, commentRequest)

        Network.request(commentRequestCall, NetworkCallback<GistComment>()
                .apply {
                    success = {
                        assertEquals(comment, it.body)
                    }

                    error = {
                        throw it
                    }
                })

    }
}