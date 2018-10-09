package com.rezolve.gistscanner.data.retrofit;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class BasicAuthenticationInterceptor implements Interceptor {

    private final String credentials;

    BasicAuthenticationInterceptor(@NonNull String user, @NonNull String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }
}

