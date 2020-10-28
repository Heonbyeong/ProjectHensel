package com.example.projecthensel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailRouteActivity extends AppCompatActivity {

    Button returnButton;
    RecyclerView detailRecyclerView;
    DetailAdapter adapter;
    TextView yearEdit, monthDate, countText2;
    String year, dateString, address, memo, startTime, endTime, count;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_route_page);
        returnButton = (Button)findViewById(R.id.detailToAllButton);
        detailRecyclerView = (RecyclerView)findViewById(R.id.detailRecyclerView);
        yearEdit = (TextView)findViewById(R.id.yearText);
        monthDate = (TextView)findViewById(R.id.monthDate);
        countText2 = (TextView)findViewById(R.id.countText2);

        adapter = new DetailAdapter();
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent4 = getIntent();

        if(intent4.hasExtra("hasData")){
            year = intent4.getExtras().getString("yearToDetail") + "년";
            dateString = intent4.getExtras().getString("dataStringToDetail");
            address = intent4.getExtras().getString("addressToDetail");
            startTime = intent4.getExtras().getString("startTimeToDetail");
            endTime = intent4.getExtras().getString("endTimeToDetail");
            memo = intent4.getExtras().getString("memoToDetail");
            count = intent4.getExtras().getString("countToDetail");

            yearEdit.setText(year);
            monthDate.setText(dateString);
            countText2.setText(count);

            detailRecyclerView.setAdapter(adapter);
            adapter.addItem(new Data2(address, startTime, endTime, memo));
        }
        returnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DetailRouteActivity.this, MainActivity.class);
                //intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent2);
//                intent2.setAction(Intent.ACTION_MAIN);
//                intent2.addCategory(Intent.CATEGORY_LAUNCHER);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });
    }
}
