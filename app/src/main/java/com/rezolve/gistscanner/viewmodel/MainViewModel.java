package com.rezolve.gistscanner.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rezolve.gistscanner.Remote;
import com.rezolve.gistscanner.data.CreateGistResponse;
import com.rezolve.gistscanner.data.GistCommentListResponse;
import com.rezolve.gistscanner.data.GistRepository;

import javax.inject.Inject;

import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Remote
    private final GistRepository gistRepository;

    @Nullable
    private MutableLiveData<GistCommentListResponse> commentListResponseMutableLiveData;

    private MutableLiveData<CreateGistResponse> addCommentMutableLiveData;

    @Inject
    public MainViewModel(GistRepository gistRepository) {
        this.gistRepository = gistRepository;
    }

    @Nullable
    public LiveData<GistCommentListResponse> getCommentListResponse(@NonNull String gistID,
                                                                    @NonNull String username,
                                                                    @NonNull String password) {
        if (commentListResponseMutableLiveData == null) {
            commentListResponseMutableLiveData = new MutableLiveData<>();
            Timber.d("Remote fetch for comment list");
            gistRepository.fetchGistCommentList(
                    gistID, username,
                    password, commentListResponseMutableLiveData::setValue);
        }
        return commentListResponseMutableLiveData;
    }

    public MutableLiveData<CreateGistResponse> getAddCommentMutableLiveData(@NonNull String gistID,
                                                                            @NonNull String username,
                                                                            @NonNull String password,
                                                                            @NonNull String comment) {
        if (addCommentMutableLiveData == null)
            addCommentMutableLiveData = new MutableLiveData<>();

        gistRepository.createdGistComment(gistID, username, password, comment,
                addCommentMutableLiveData::setValue);


        return addCommentMutableLiveData;
    }

    public void invalidateCommentList() {
        commentListResponseMutableLiveData = null;
    }
}
