package com.rezolve.gistscanner.model;

import android.support.annotation.Nullable;

import com.rezolve.gistscanner.api.GistApiResponse;

import java.util.List;

public class GistCommentListResponse extends GistApiResponse<List<GistComment>, Throwable> {

    public GistCommentListResponse(@Nullable List<GistComment> response, @Nullable Throwable throwable) {
        super(response, throwable);
    }
}
