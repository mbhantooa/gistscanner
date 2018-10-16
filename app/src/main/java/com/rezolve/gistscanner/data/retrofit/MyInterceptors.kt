package com.rezolve.gistscanner.data.retrofit

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Basic authorization interceptor.
 *
 * Very simple and username and password are needed.
 */
internal class BasicAuthenticationInterceptor(private val user: String,
                                              private val password: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
                .request()
                .newBuilder()
                .header("Authorization", Credentials.basic(user, password))
                .build()
        return chain.proceed(request)
    }
}