package com.rezolve.gistscanner.viewmodel;

import android.databinding.BaseObservable;

import com.rezolve.gistscanner.model.GistComment;
import com.rezolve.gistscanner.model.User;

public class GistCommentDataBindingViewModel extends BaseObservable {
    private final GistComment gistComment;

    public GistCommentDataBindingViewModel(GistComment gistComment) {
        this.gistComment = gistComment;
    }

    public String getBody() {
        return gistComment.getBody();
    }

    public String getLastUpdatedAt() {
        return gistComment.getUpdatedAt();
    }

    public User getUser() {
        return gistComment.getUser();
    }
}
