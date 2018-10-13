package com.rezolve.gistscanner.viewmodel;

import android.databinding.BaseObservable;

import com.rezolve.gistscanner.model.GistComment;
import com.rezolve.gistscanner.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class GistCommentDataBindingViewModel extends BaseObservable {
    private final GistComment gistComment;

    private static final SimpleDateFormat dateFormatSource =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    private static final SimpleDateFormat dateFormatSink =
            new SimpleDateFormat("dd MMM yyyy 'at' HH:mm", Locale.US);

    public GistCommentDataBindingViewModel(GistComment gistComment) {
        this.gistComment = gistComment;
    }

    public String getBody() {
        return gistComment.getBody();
    }

    public String getLastUpdatedAt() {

        try {
            Date date = dateFormatSource
                    .parse(gistComment.getUpdatedAt().replace("T", " "));

            return dateFormatSink.format(date);
        } catch (ParseException e) {
            return gistComment.getUpdatedAt();
        }
    }

    public User getUser() {
        return gistComment.getUser();
    }
}
