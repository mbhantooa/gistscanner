package com.rezolve.gistscanner.model;

import com.rezolve.gistscanner.api.GistApiResponse;

import org.jetbrains.annotations.Nullable;

public class CreateGistResponse extends GistApiResponse<GistComment, Throwable> {
    public CreateGistResponse(@Nullable GistComment response, @Nullable Throwable exception) {
        super(response, exception);
    }
}
