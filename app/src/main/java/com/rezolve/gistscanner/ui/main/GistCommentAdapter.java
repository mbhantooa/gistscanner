package com.rezolve.gistscanner.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rezolve.gistscanner.R;
import com.rezolve.gistscanner.databinding.GistCommentListItemBinding;
import com.rezolve.gistscanner.model.GistComment;
import com.rezolve.gistscanner.viewmodel.GistCommentDataBindingViewModel;

import java.util.List;

public class GistCommentAdapter extends RecyclerView.Adapter<GistCommentAdapter.GistCommentViewHolder> {

    @Nullable
    private List<? extends GistComment> gistCommentList;

    public void setGistCommentList(@Nullable List<? extends GistComment> gistCommentList) {
        this.gistCommentList = gistCommentList;
        if (gistCommentList != null) {
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public GistCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater
                .from(parent.getContext());
        GistCommentListItemBinding gistCommentListItemBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.gist_comment_list_item, parent, false
        );
        return new GistCommentViewHolder(gistCommentListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GistCommentViewHolder gistCommentViewHolder, int position) {
        gistCommentViewHolder
                .gistCommentListItemBinding
                .setComment(new GistCommentDataBindingViewModel(gistCommentList.get(position)));
    }

    @Override
    public int getItemCount() {
        return gistCommentList == null ? 0 : gistCommentList.size();
    }

    public static class GistCommentViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        final GistCommentListItemBinding gistCommentListItemBinding;

        public GistCommentViewHolder(final GistCommentListItemBinding gistCommentListItemBinding) {
            super(gistCommentListItemBinding.getRoot());
            this.gistCommentListItemBinding = gistCommentListItemBinding;
        }
    }
}

