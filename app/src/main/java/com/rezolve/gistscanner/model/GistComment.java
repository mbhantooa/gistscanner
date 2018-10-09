package com.rezolve.gistscanner.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GistComment {

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("node_id")
    @Expose
    private String nodeId;

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("author_association")
    @Expose
    private String authorAssociation;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("body")
    @Expose
    private String body;

    public String getUrl() {
        return url;
    }

    public Integer getId() {
        return id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public User getUser() {
        return user;
    }

    public String getAuthorAssociation() {
        return authorAssociation;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getBody() {
        return body;
    }

    @NonNull
    @Override
    public String toString() {
        return "GistComment{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", nodeId='" + nodeId + '\'' +
                ", user=" + user +
                ", authorAssociation='" + authorAssociation + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
