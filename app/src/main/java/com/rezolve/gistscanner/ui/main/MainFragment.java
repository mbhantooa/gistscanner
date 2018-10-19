package com.rezolve.gistscanner.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.data.GistCommentListResponse;
import com.rezolve.gistscanner.databinding.MainFragmentBinding;
import com.rezolve.gistscanner.di.ActivityScoped;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import timber.log.Timber;

import com.rezolve.gistscanner.ui.util.RightDrawableOnTouchListener;
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

    private EditText editTextComment;
    private ProgressBar progressCircular;

    private MainViewModel mainViewModel;

    @Nullable
    private String gistId;

    @Inject
    public MainFragment() {
        setArguments(new Bundle());
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.main_fragment, container, false);
        progressCircular = mainFragmentBinding.getRoot().findViewById(R.id.progress_circular);
        editTextComment = mainFragmentBinding.getRoot().findViewById(R.id.editTextComment);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            editTextComment.setClipToOutline(true);
            editTextComment.setOnTouchListener(new RightDrawableOnTouchListener(editTextComment) {
                @Override
                public boolean onDrawableTouch(@NonNull MotionEvent event) {
                    return onTapSend(editTextComment, event);
                }
            });
        }
        editTextComment.requestFocus();
        return mainFragmentBinding.getRoot();
    }

    private boolean onTapSend(final EditText editText, @NonNull MotionEvent event) {
        if (TextUtils.isEmpty(editText.getText())) {
            Toast.makeText(getContext(), R.string.edit_text_validation, Toast.LENGTH_SHORT).show();
            return false;
        }

        event.setAction(MotionEvent.ACTION_CANCEL);
        sendComment(editText, editText.getText().toString());
        return true;
    }

    private void sendComment(EditText editText, @NonNull String comment) {
        editText.setEnabled(false);
        progressCircular.setVisibility(View.VISIBLE);
        mainViewModel.invalidateCommentList();
        mainViewModel.getAddCommentMutableLiveData(gistId, USERNAME, PASSWORD, comment)
                .observe(this, (createGistResponse -> {
                    fetchCommentList(); // will refresh the list
                    editTextComment.setText("");
                    editText.setEnabled(true);
                    progressCircular.setVisibility(View.GONE);
                }));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getView() == null || getContext() == null
                || getArguments() == null) {
            return;
        }

        gistId = getArguments().getString(GIST_ID_BUNDLE_ARGUMENT);

        if (gistId == null)
            return;

        mainFragmentBinding.setAdapter(new GistCommentAdapter());
        mainFragmentBinding.setDividerItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mainFragmentBinding.setLayoutManager(new LinearLayoutManager(getContext()));


        // View Model
        mainViewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(MainViewModel.class);


        fetchCommentList();
    }

    private void fetchCommentList() {
        LiveData<GistCommentListResponse> listResponseLiveData =
                mainViewModel.getCommentListResponse(gistId, USERNAME, PASSWORD);
        if (listResponseLiveData.getValue() != null) {
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
