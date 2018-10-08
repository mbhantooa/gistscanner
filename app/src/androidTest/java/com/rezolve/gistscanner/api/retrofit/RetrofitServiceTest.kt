package com.rezolve.gistscanner.api.retrofit

import com.rezolve.gistscanner.data.retrofit.RetrofitService
import com.rezolve.gistscanner.data.retrofit.Util
import com.rezolve.gistscanner.model.CommentRequest
import com.rezolve.gistscanner.model.GistComment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RetrofitServiceTest {

    private val gistID = "92c0e856c23d0c8c6c26611028a32089"

    private val retrofitService = Util.createService(
            RetrofitService::class.java, "mbhantooa", "P@ranoid2018"
    )

    @Test
    fun gist_comment_list_first_item() {
        val commentListCall = retrofitService.getGistComments(gistID)
        val response = commentListCall.execute()

        // We actually have a response
        assertNotNull(response)

        // Request is correctly constructed
        assertTrue(response.isSuccessful)

        // Body should not be null
        assertNotNull(response.body())
        val responseBody = response.body()

        responseBody?.let {
            assertFalse(it.isEmpty())
            val firstGistComment = it[0]

            assertNotNull(firstGistComment)
            assertEquals(2725937, firstGistComment.id)
            assertEquals("This is a small wrapper class that uses generics to callers determine the type of the actual response and the exception cases.",
                    firstGistComment.body)
        }
    }

    @Test
    fun gist_comment_list_first_item_user_details() {
        val commentListCall = retrofitService.getGistComments(gistID)
        val response = commentListCall.execute()

        // We actually have a response
        assertNotNull(response)

        // Request is correctly constructed
        assertTrue(response.isSuccessful)

        // Body should not be null
        assertNotNull(response.body())
        val responseBody = response.body()

        responseBody?.let {
            assertFalse(it.isEmpty())

            val firstGistComment = it[0]

            assertNotNull(firstGistComment)
            assertEquals(25606240, firstGistComment.user.id)
            assertEquals("mbhantooa", firstGistComment.user.login)
        }
    }

    @Test
    fun gist_create_comment() {
        val comment = "Comment added through testing"
        val commentRequest = CommentRequest(comment)
        val commentRequestCall = retrofitService.createComment(gistID, commentRequest)
        val response = commentRequestCall.execute()

        // We actually have a response
        assertNotNull(response)

        // Request is correctly constructed
        assertTrue(response.isSuccessful)

        // Body should not be null
        assertNotNull(response.body())
        val responseBody: GistComment? = response.body()

        responseBody?.let {
            assertEquals(comment, it.body)
        }
    }
}