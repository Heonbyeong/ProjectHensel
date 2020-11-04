    package com.example.projecthensel.Recycler;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthensel.DetailRouteActivity;
import com.example.projecthensel.OnDataItemClickListener;
import com.example.projecthensel.R;

import java.util.ArrayList;

public class DateMainAdapter extends RecyclerView.Adapter<DateMainAdapter.ViewHolder>
                            implements OnDataItemClickListener {
    ArrayList<DateMain> items = new ArrayList<>();
    OnDataItemClickListener listener;
    public void addItem(DateMain item) {items.add(item);}
    @NonNull
    @Override
    public DateMainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recyclerview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DateMainAdapter.ViewHolder holder, int position) {
        DateMain item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(DateMainAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateText, count;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            dateText = itemView.findViewById(R.id.dateText);
            count = itemView.findViewById(R.id.countText);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        DateMain item = items.get(position);
                        Intent intent = new Intent(v.getContext(), DetailRouteActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        v.getContext().startActivity(intent);
                    }
                }
            });

        }

        public void setItem(DateMain item){
            dateText.setText(item.getData());
            count.setText(Integer.toString(item.getCount()));
        }
    }
}

