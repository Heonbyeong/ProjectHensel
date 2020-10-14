package com.example.projecthensel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder>
                            implements OnDataItemClickListener {

    private ArrayList<Data> Data = new ArrayList<Data>();
    OnDataItemClickListener listener;

    public void addItem(Data data){
        Data.add(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.recyclerview, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        Data item = Data.get(position);
        viewholder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTextView;
        private TextView countTextView;

        public ViewHolder(@NonNull View itemView, final  OnDataItemClickListener listener) {
            super(itemView);

            dateTextView = itemView.findViewById(R.id.dateText);
            countTextView = itemView.findViewById(R.id.countText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Data item) {
            dateTextView.setText(item.getData());
            countTextView.setText(item.getCount());
        }
    }
}

