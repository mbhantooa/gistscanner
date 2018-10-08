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

@ActivityScoped
public class MainFragment extends DaggerFragment {

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

        mainViewModel.micCheck();
    }

}
