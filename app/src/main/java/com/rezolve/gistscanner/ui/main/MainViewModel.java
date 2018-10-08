package com.rezolve.gistscanner.ui.main;

import android.arch.lifecycle.ViewModel;

import com.rezolve.gistscanner.data.GistRepository;
import com.rezolve.gistscanner.data.Remote;

import javax.inject.Inject;

import timber.log.Timber;

public class MainViewModel extends ViewModel {

    @Remote
    private GistRepository gistRepository;

    @Inject
    public MainViewModel(GistRepository gistRepository) {
        this.gistRepository = gistRepository;
    }

    public void micCheck() {
        Timber.d("My respository: " + gistRepository.toString());
    }
}
