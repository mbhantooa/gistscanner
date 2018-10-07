package com.rezolve.gistscanner.model;

import com.rezolve.gistscanner.api.GistApiResponse;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GistCommentListResponse extends GistApiResponse<List<GistComment>, Throwable> {

    public GistCommentListResponse(@Nullable List<GistComment> response, @Nullable Throwable throwable) {
        super(response, throwable);
    }
}
