package com.example.projecthensel;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthensel.Recycler.DateDetail;
import com.example.projecthensel.Recycler.DetailDateAdapter;
import com.example.projecthensel.Room.AppDatabase;
import com.example.projecthensel.Room.Date;

import java.util.List;

public class DetailRouteActivity extends AppCompatActivity {

    Button returnButton, deleteAll;
    RecyclerView detailRecyclerView;
    DetailDateAdapter adapter;
    TextView yearEdit, monthDate, countText2;
    String year, fullDate, address, memo, startTime, endTime, count;
    Date date;
    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_route_page);
        returnButton = (Button)findViewById(R.id.detailToAllButton);
        detailRecyclerView = (RecyclerView)findViewById(R.id.detailRecyclerView);
        yearEdit = (TextView)findViewById(R.id.yearText);
        monthDate = (TextView)findViewById(R.id.monthDate);
        countText2 = (TextView)findViewById(R.id.countText2);
        deleteAll = (Button)findViewById(R.id.delAllButton);

        adapter = new DetailDateAdapter(db);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        detailRecyclerView.setLayoutManager(layoutManager);
        detailRecyclerView.setAdapter(adapter);
        db = AppDatabase.getDatabase(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(DetailRouteActivity.this);
        Intent intent4 = getIntent();
        //메인액티비티에서 데이터 가져오기
        if(intent4.hasExtra("hasData")){
//            year = intent4.getExtras().getString("yearToDetail") + "년";
//            fullDate = intent4.getExtras().getString("fullDateToDetail");
//            address = intent4.getExtras().getString("addressToDetail");
//            startTime = intent4.getExtras().getString("startTimeToDetail") + " AM";
//            endTime = intent4.getExtras().getString("endTimeToDetail") + " PM";
//            memo = intent4.getExtras().getString("memoToDetail");
//            count = intent4.getExtras().getString("countToDetail");

            yearEdit.setText(date.getYear());
            monthDate.setText(date.getFullDate());
            countText2.setText(date.getCount());

        }

        new Thread(() -> {
            List<Date> dateList = db.dateDao().getAll();
            adapter.setItem(dateList);
        }).start();
        //메인으로 돌아가기
        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DetailRouteActivity.this, MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                builder.setTitle("정말 삭제하시겠습니까?").setMessage("현재 날짜의 모든 기록이 삭제됩니다.");
                builder.setCancelable(false);
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        db.dateDao().delete(adapter.getItems().get(position));
                        startActivity(intent);
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
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
