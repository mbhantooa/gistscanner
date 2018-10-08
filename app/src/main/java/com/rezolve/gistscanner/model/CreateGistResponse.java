package com.rezolve.gistscanner.model;

import android.support.annotation.Nullable;

import com.rezolve.gistscanner.data.GistApiResponse;

public class CreateGistResponse extends GistApiResponse<GistComment, Throwable> {
    public CreateGistResponse(@Nullable GistComment response, @Nullable Throwable exception) {
        super(response, exception);
    }
}
