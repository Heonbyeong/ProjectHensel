package com.example.projecthensel.Recycler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthensel.MainActivity;
import com.example.projecthensel.OnDataItemClickListener;
import com.example.projecthensel.R;
import com.example.projecthensel.Room.AppDatabase;
import com.example.projecthensel.Room.Date;

import java.util.ArrayList;
import java.util.List;

public class DetailDateAdapter extends RecyclerView.Adapter<DetailDateAdapter.ViewHolder> {

    private List<Date> items = new ArrayList<>();
    OnDataItemClickListener listener;
    private AppDatabase db;

    public DetailDateAdapter(AppDatabase db) {this.db = db;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView addressText, startTime, endTime, memoText;
        ImageButton delButton;
        Context context;
        private int index;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            addressText = itemView.findViewById(R.id.address_text);
            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            memoText = itemView.findViewById(R.id.memo_text);
            delButton = itemView.findViewById(R.id.delButton);

            final AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            final Intent intent = new Intent(itemView.getContext() , MainActivity.class);
            context = itemView.getContext();

            //경로 삭제버튼 클릭시 띄우는 팝업창
            delButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    builder.setTitle("정말 삭제하시겠습니까?").setMessage("선택하신 기록이 삭제됩니다.");
                    builder.setCancelable(false);
                    builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removeItem(getAdapterPosition());
                            intent.putExtra("removeItem", "true");
                            context.startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                }
            });
        }

        public void onBind(Date date, int position){
            index = position;
            addressText.setText(date.getAddress());
            startTime.setText(date.getStartTime());
            endTime.setText(date.getEndTime());
            memoText.setText(date.getMemo());
        }

        public void removeItem(int position){
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setItem(List<Date> item){
        items = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailDateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.detail_recyclerview, parent, false);
        DetailDateAdapter.ViewHolder viewHolder = new DetailDateAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailDateAdapter.ViewHolder holder, int position) {
        holder.onBind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Date> getItems() {return items;}
}
