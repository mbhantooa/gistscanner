package com.rezolve.gistscanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class is used for creating a comment on a gist.
 */
public class CommentRequest {

    @SerializedName("body")
    @Expose
    private final String body;

    public CommentRequest(String body) {
        this.body = body;
    }
}
