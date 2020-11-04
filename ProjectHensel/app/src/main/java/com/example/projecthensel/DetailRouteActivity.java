package com.example.projecthensel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecthensel.Recycler.DateDetail;
import com.example.projecthensel.Recycler.DetailDateAdapter;

public class DetailRouteActivity extends AppCompatActivity {

    Button returnButton;
    RecyclerView detailRecyclerView;
    DetailDateAdapter adapter;
    TextView yearEdit, monthDate, countText2;
    String year, fullDate, address, memo, startTime, endTime, count;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_route_page);
        returnButton = (Button)findViewById(R.id.detailToAllButton);
        detailRecyclerView = (RecyclerView)findViewById(R.id.detailRecyclerView);
        yearEdit = (TextView)findViewById(R.id.yearText);
        monthDate = (TextView)findViewById(R.id.monthDate);
        countText2 = (TextView)findViewById(R.id.countText2);

        adapter = new DetailDateAdapter();
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent4 = getIntent();

        //메인액티비티에서 데이터 가져오기
        if(intent4.hasExtra("hasData")){
            year = intent4.getExtras().getString("yearToDetail") + "년";
            fullDate = intent4.getExtras().getString("fullDateToDetail");
            address = intent4.getExtras().getString("addressToDetail");
            startTime = intent4.getExtras().getString("startTimeToDetail") + " AM";
            endTime = intent4.getExtras().getString("endTimeToDetail") + " PM";
            memo = intent4.getExtras().getString("memoToDetail");
            count = intent4.getExtras().getString("countToDetail");

            yearEdit.setText(year);
            monthDate.setText(fullDate);
            countText2.setText(count);

            detailRecyclerView.setAdapter(adapter);
            adapter.addItem(new DateDetail(address, startTime, endTime, memo));
        }

        //메인으로 돌아가기
        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DetailRouteActivity.this, MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
            }
        });

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
