package com.rezolve.gistscanner.data;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

/**
 * Abstraction to hold an API service call response. There can be a response R or an exception of
 * type E. The subclasses will decide the appropriate types.
 *
 * @param <R> the API documented response type
 * @param <E> a convenient exception type
 */
public class GistApiResponse<R, E extends Throwable> {
    private final R response;
    private final E exception;

    public GistApiResponse(@Nullable R response,
                           @Nullable E exception) {
        this.response = response;
        this.exception = exception;

        if ((response == null) == (exception == null)) {
            throw new IllegalArgumentException("At least one of R or E should not be null.");
        }
    }

    public R getResponse() {
        return response;
    }

    public E getException() {
        return exception;
    }

    @NonNull
    @Override
    public String toString() {
        return "GistApiResponse{" +
                "response=" + response +
                ", exception=" + exception +
                '}';
    }

    public boolean isSuccessful() {
        return response != null && exception == null;
    }
}
