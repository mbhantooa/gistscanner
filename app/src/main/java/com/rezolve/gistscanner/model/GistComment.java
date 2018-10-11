package com.rezolve.gistscanner.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GistComment extends BaseObservable {

    @SerializedName("user")
    @Expose
    @Bindable
    private User user;

    @SerializedName("updated_at")
    @Expose
    @Bindable
    private String updatedAt;

    @SerializedName("body")
    @Expose
    @Bindable
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
