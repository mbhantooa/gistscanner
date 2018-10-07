package com.rezolve.gistscanner.api

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.lang.IllegalArgumentException

class GistApiResponseTest {

    @Test
    fun api_response_args_with_success_response() {
        val response = "Success"
        val exception = null

        val stringExceptionGistApiResponse = GistApiResponse(response, exception)
        assertNotNull(stringExceptionGistApiResponse)

        assertTrue(stringExceptionGistApiResponse.isSuccessful)
    }

    @Test
    fun api_response_args_with_exception() {
        val response = null
        val exception = IOException()

        val stringExceptionGistApiResponse = GistApiResponse(response, exception)
        assertNotNull(stringExceptionGistApiResponse)

        assertFalse(stringExceptionGistApiResponse.isSuccessful)
    }

    @Test(expected = IllegalArgumentException::class)
    fun api_response_args_with_no_valid_args() {
        val response = null
        val exception = null

        GistApiResponse(response, exception)
    }

    @Test(expected = IllegalArgumentException::class)
    fun api_response_args_with_both_valid_args() {
        val response = "success"
        val exception = IOException()

        GistApiResponse(response, exception)
    }
}