package com.rezolve.gistscanner.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.databinding.MainFragmentBinding;
import com.rezolve.gistscanner.di.ActivityScoped;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;

import com.rezolve.gistscanner.ui.util.ViewModelFactory;
import com.rezolve.gistscanner.viewmodel.MainViewModel;

@ActivityScoped
public class MainFragment extends DaggerFragment {

    public static final String GIST_ID_BUNDLE_ARGUMENT = "gist_argument";

    private static final String USERNAME = "mbhantooa";
    private static final String PASSWORD = "P@ranoid2018";

    private MainFragmentBinding mainFragmentBinding;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    public MainFragment() {
        setArguments(new Bundle());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.main_fragment, container, false);
        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getView() == null || getContext() == null
                || getArguments() == null) {
            return;
        }

        String gistId = getArguments().getString(GIST_ID_BUNDLE_ARGUMENT);

        if (gistId == null)
            return;

        mainFragmentBinding.setAdapter(new GistCommentAdapter());
        mainFragmentBinding.setDividerItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mainFragmentBinding.setLayoutManager(new LinearLayoutManager(getContext()));


        // View Model
        MainViewModel mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel.class);

        ProgressBar progressCircular = getView().findViewById(R.id.progress_circular);

        if (mainViewModel
                .getCommentListResponse(gistId, USERNAME, PASSWORD)
                .getValue() != null) {
            progressCircular.setVisibility(View.GONE);
        } else {
            progressCircular.setVisibility(View.VISIBLE);
        }

        mainViewModel.getCommentListResponse(gistId, USERNAME, PASSWORD)
                .observe(this, (gistCommentListResponse -> {
                    progressCircular.setVisibility(View.GONE);
                    if (gistCommentListResponse != null) {
                        Timber.d(gistCommentListResponse.toString());
                        if (gistCommentListResponse.isSuccessful()) {
                            mainFragmentBinding
                                    .getAdapter()
                                    .setGistCommentList(gistCommentListResponse.getResponse());
                        } else {
                            Toast.makeText(getContext(), R.string.gist_id_validation, Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
    }

}
