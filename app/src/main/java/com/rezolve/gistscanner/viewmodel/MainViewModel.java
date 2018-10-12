package com.rezolve.gistscanner.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rezolve.gistscanner.data.GistRepository;
import com.rezolve.gistscanner.data.Remote;
import com.rezolve.gistscanner.model.CreateGistResponse;
import com.rezolve.gistscanner.model.GistCommentListResponse;

import javax.inject.Inject;

import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Remote
    private final GistRepository gistRepository;

    private final MutableLiveData<GistCommentListResponse> commentListResponseMutableLiveData
            = new MutableLiveData<>();

    private final MutableLiveData<CreateGistResponse> addCommentMutableLiveData
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
        if (addCommentMutableLiveData.getValue() != null)
            addCommentMutableLiveData.setValue(null);

        gistRepository.createdGistComment(gistID, username, password, comment,
                addCommentMutableLiveData::setValue);


        return addCommentMutableLiveData;
    }

    public void invalidateCommentList() {
        commentListResponseMutableLiveData.setValue(null);
    }


}
