package com.example.projecthensel;

import android.view.View;

import com.example.projecthensel.Recycler.MainDateAdapter;

public interface OnDataItemClickListener {
    public void onItemClick(MainDateAdapter.ViewHolder holder, View view, int position);
}
