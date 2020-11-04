package com.example.projecthensel;

import android.view.View;

import com.example.projecthensel.Recycler.DateMainAdapter;

public interface OnDataItemClickListener {
    public void onItemClick(DateMainAdapter.ViewHolder holder, View view, int position);
}
