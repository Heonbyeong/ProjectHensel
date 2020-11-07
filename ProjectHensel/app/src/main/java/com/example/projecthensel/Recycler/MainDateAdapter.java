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
import com.example.projecthensel.Room.AppDatabase;
import com.example.projecthensel.Room.Date;

import java.util.ArrayList;
import java.util.List;

    public class MainDateAdapter extends RecyclerView.Adapter<MainDateAdapter.ViewHolder>
                            implements OnDataItemClickListener {
    private List<Date> items = new ArrayList<>();
    OnDataItemClickListener listener;
    private AppDatabase db;
    public void addItem(Date item) {items.add(item);}

    public MainDateAdapter(AppDatabase db){
        this.db = db;
    }

    @NonNull
    @Override
    public MainDateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recyclerview, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainDateAdapter.ViewHolder holder, int position) {
        holder.onBind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(MainDateAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateText, count;
        private int index;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            dateText = itemView.findViewById(R.id.dateText);
            count = itemView.findViewById(R.id.countText);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION){
                        Date item = items.get(position);
                        Intent intent = new Intent(v.getContext(), DetailRouteActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        v.getContext().startActivity(intent);
                    }
                }
            });

        }

        public void onBind(Date date, int position){
            index = position;
            dateText.setText(date.getFullDate());
            count.setText(Integer.toString(date.getCount()));
        }
        
    }
        public void setItem(List<Date> item){
            items = item;
            notifyDataSetChanged();
        }
}

