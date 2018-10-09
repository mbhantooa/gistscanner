package com.rezolve.gistscanner.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.di.ActivityScoped;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;
import util.ViewModelFactory;

@ActivityScoped
public class MainFragment extends DaggerFragment {

    private static final String GIST_ID = "92c0e856c23d0c8c6c26611028a32089";
    private static final String USERNAME = "mbhantooa";
    private static final String PASSWORD = "P@ranoid2018";

    @Inject
    ViewModelFactory viewModelFactory;

    private MainViewModel mainViewModel;

    @Inject
    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel.class);

        mainViewModel.getCommentListResponse(GIST_ID, USERNAME, PASSWORD)
                .observe(this, (gistCommentListResponse -> {
                    if (gistCommentListResponse != null)
                        Timber.d("Found comments: " + gistCommentListResponse.toString());
                }));
    }

}
