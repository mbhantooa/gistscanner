package com.rezolve.gistscanner.api.retrofit;

import androidx.annotation.Nullable;

import com.rezolve.gistscanner.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final class Util {

    private static final String API_BASE_URL = "https://api.github.com";

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    static <T> T createService(Class<T> serviceClass,
                               @Nullable String username, @Nullable String password) {

        // Basic authentication
        if (username != null && password != null) {
            BasicAuthenticatorInterceptor interceptor =
                    new BasicAuthenticatorInterceptor(username, password);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
            }
        }

        // Logging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE;
        loggingInterceptor.setLevel(level);
        httpClient.addInterceptor(loggingInterceptor);

        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);

    }
}
