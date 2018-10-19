package com.rezolve.gistscanner.ui.main;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

public class DataBinder {

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView recyclerView, GistCommentAdapter adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter("dividerItemDecoration")
    public static void setDividerItemDecoration(RecyclerView recyclerView, @NonNull DividerItemDecoration dividerItemDecoration) {
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
