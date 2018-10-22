package com.rezolve.gistscanner.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rezolve.gistscanner.Remote;
import com.rezolve.gistscanner.data.GistRepository;
import com.rezolve.gistscanner.data.NetworkCallback;
import com.rezolve.gistscanner.model.GistComment;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Remote
    private final GistRepository gistRepository;

    @Nullable
    private MutableLiveData<List<GistComment>> commentListMutableLiveData;

    private MutableLiveData<GistComment> addCommentMutableLiveData;

    @Inject
    public MainViewModel(GistRepository gistRepository) {
        this.gistRepository = gistRepository;
    }

    @Nullable
    public LiveData<List<GistComment>> getCommentListResponse(@NonNull String gistID,
                                                              @NonNull String username,
                                                              @NonNull String password) {
        if (commentListMutableLiveData == null) {
            commentListMutableLiveData = new MutableLiveData<>();
            Timber.d("Remote fetch for comment list");
            gistRepository.fetchGistCommentList(
                    gistID, username,
                    password, null);
        }
        return commentListMutableLiveData;
    }

    public MutableLiveData<GistComment> getAddCommentMutableLiveData(@NonNull String gistID,
                                                                     @NonNull String username,
                                                                     @NonNull String password,
                                                                     @NonNull String comment) {
        if (addCommentMutableLiveData == null)
            addCommentMutableLiveData = new MutableLiveData<>();

        gistRepository.createdGistComment(gistID, username, password, comment,
                null);


        return addCommentMutableLiveData;
    }

    public void invalidateCommentList() {
        commentListMutableLiveData = null;
    }
}
