package com.example.projecthensel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    ArrayList<Data2> items = new ArrayList<>();
    OnDataItemClickListener listener;

    public void addItem(Data2 data2){
        items.add(data2);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView addressText, startTime, endTime, memoText2;
        ImageButton delButton;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            addressText = itemView.findViewById(R.id.textView4);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            memoText2 = itemView.findViewById(R.id.memoText2);
            delButton = itemView.findViewById(R.id.delButton);

            final AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            final AlertDialog dialog = builder.create();

            delButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    builder.setTitle("정말 삭제하시겠습니까?").setMessage("선택하신 기록이 삭제됩니다.");
                    builder.setCancelable(false);
//                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                    builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removeItem(getAdapterPosition());
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            });
        }

        public void setItem(Data2 item){
            addressText.setText(item.getAddress());
            startTime.setText(item.getStartTime());
            endTime.setText(item.getEndTime());
            memoText2.setText(item.getMemo());
        }

        public void removeItem(int position){
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

//    DetailAdapter(ArrayList<Data2> list){
//        items = list;
//    }

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
