package com.rezolve.gistscanner.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rezolve.gistscanner.data.GistRepository;
import com.rezolve.gistscanner.data.Remote;
import com.rezolve.gistscanner.model.GistCommentListResponse;

import javax.inject.Inject;

import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Remote
    private GistRepository gistRepository;

    private final MutableLiveData<GistCommentListResponse> commentListResponseMutableLiveData
            = new MutableLiveData<>();

    @Inject
    public MainViewModel(GistRepository gistRepository) {
        this.gistRepository = gistRepository;
    }

    public LiveData<GistCommentListResponse> getCommentListResponse(@NonNull String gistID,
                                                                    @NonNull String username,
                                                                    @NonNull String password) {
        if (commentListResponseMutableLiveData.getValue() == null) {
            Timber.d("Remote fetch for comment list");
            gistRepository.fetchGistCommentList(gistID, username, password, (commentListResponseMutableLiveData::setValue));
        }
        return commentListResponseMutableLiveData;
    }
}
