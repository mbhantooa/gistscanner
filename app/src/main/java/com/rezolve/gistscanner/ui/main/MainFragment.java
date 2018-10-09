package com.rezolve.gistscanner.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

        if (getView() == null) {
            return;
        }

        // UI set up
        ProgressBar progressBar = ProgressBar.class
                .cast(getView().findViewById(R.id.progress_circular));

        RecyclerView recyclerView = RecyclerView.class
                .cast(getView().findViewById(R.id.recycler_view_gist_comments));

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        GistCommentAdapter gistCommentAdapter = new GistCommentAdapter();
        recyclerView.setAdapter(gistCommentAdapter);


        // View Model
        MainViewModel mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel.class);

        mainViewModel.getCommentListResponse(GIST_ID, USERNAME, PASSWORD)
                .observe(this, (gistCommentListResponse -> {
                    progressBar.setVisibility(View.GONE);
                    if (gistCommentListResponse != null) {
                        Timber.d("Found comments: " + gistCommentListResponse.toString());
                        if (gistCommentListResponse.isSuccessful()) {
                            gistCommentAdapter.setGistCommentList(gistCommentListResponse.getResponse());
                        }
                    }
                }));
    }

}
