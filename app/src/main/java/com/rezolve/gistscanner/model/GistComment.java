package com.rezolve.gistscanner.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GistComment {

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("body")
    @Expose
    private String body;

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "GistComment{" +
                ", user=" + user +
                ", updatedAt='" + updatedAt + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
