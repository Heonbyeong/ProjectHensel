package com.example.projecthensel;

import android.view.View;

import com.example.projecthensel.Recycler.DateAdapter;

public interface OnDataItemClickListener {
    public void onItemClick(DateAdapter.ViewHolder holder, View view, int position);
}
