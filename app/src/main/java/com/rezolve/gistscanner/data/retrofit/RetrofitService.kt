package com.rezolve.gistscanner.data.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rezolve.gistscanner.model.CommentRequest
import com.rezolve.gistscanner.model.GistComment
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val API_BASE_URL: String = "https://api.github.com"

internal interface RetrofitService {
    @GET("gists/{gistID}/comments")
    fun getGistComments(@Path("gistID") gistID: String): Deferred<List<GistComment>>

    @POST("gists/{gistID}/comments")
    fun createComment(@Path("gistID") gistID: String, @Body commentRequest: CommentRequest): Deferred<GistComment>
}

fun <T> createService(serviceClass: Class<T>, username: String, password: String): T {
    val builder: Retrofit.Builder = Retrofit.Builder()
    builder.baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

    val client = OkHttpClient.Builder()
    client.addLoggingInterceptor(HttpLoggingInterceptor.Level.BODY)
    client.addBasicAuthInterceptor(username, password)

    return builder.client(client.build())
            .build()
            .create(serviceClass)
}