package com.example.projecthensel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    public void addItem(Data2 data2){
        items.add(data2);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView addressText, startTime, endTime, memoText2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            addressText = itemView.findViewById(R.id.textView4);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            memoText2 = itemView.findViewById(R.id.memoText2);
        }

        public void setItem(Data2 item){
            addressText.setText(item.getAddress());
            startTime.setText(item.getStartTime());
            endTime.setText(item.getEndTime());
            memoText2.setText(item.getMemo());
        }
    }

//    DetailAdapter(ArrayList<Data2> list){
//        items = list;
//    }
    ArrayList<Data2> items = new ArrayList<>();
    OnDataItemClickListener listener;

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.detail_recyclerview, parent, false);
        DetailAdapter.ViewHolder viewHolder = new DetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        Data2 item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
