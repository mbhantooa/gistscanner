package com.rezolve.gistscanner.data.retrofit

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

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

fun OkHttpClient.Builder.addBasicAuthInterceptor(user: String, password: String) {
    val interceptor = BasicAuthenticationInterceptor(user, password)

    if (!interceptors().contains(interceptor))
        addInterceptor(interceptor)
}

fun OkHttpClient.Builder.addLoggingInterceptor(level: HttpLoggingInterceptor.Level) {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = level
    addInterceptor(interceptor)
}
