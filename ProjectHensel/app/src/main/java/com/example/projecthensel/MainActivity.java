package com.example.projecthensel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton ImageButton;
    DateAdapter adapter = new DateAdapter();
    Toast toast2;
    String year, month, date, dataString; // Adding 페이지에서 받아오는 값을 저장
    String memo,address, startTime, endTime; // Detail 페이지로 넘길 값을 저장
    Boolean bool;
    private long backKeyPressedTime = 0; // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton = findViewById(R.id.editImageButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        final Intent intent = getIntent();
        Intent intent3 = new Intent(getApplicationContext(), DetailRouteActivity.class);

        if(intent.hasExtra("bool"))
       {
           //add Route 페이지에서 데이터 받아오기
           getData(intent);

           //Main -> Detail 페이지로 데이터 전달
           insertData(intent3);

           intent3.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
           startActivity(intent3);
           adapter.addItem(new Data(dataString, count));

        }

        //이미지 버튼 클릭시 Add 페이지로 이동
            ImageButton.setOnClickListener(new View.OnClickListener(){     //add Route 페이지로 이동
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, RouteAddingActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){

        //2500ms = 2.5sec
        if(System.currentTimeMillis() > backKeyPressedTime + 2500){
            backKeyPressedTime = System.currentTimeMillis();
            toast2 = Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
            toast2.show();
            return;
        }
        //마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 비교 후 2.5초가 지나지 않았으면 종료
        if(System.currentTimeMillis() <= backKeyPressedTime + 2500){
            finishAffinity();
        }
        
    }

    public void getData(Intent intent){
        bool = intent.getExtras().getBoolean("bool");
        year = intent.getExtras().getString("year");
        month = intent.getExtras().getString("month");
        date = intent.getExtras().getString("date");
        count = intent.getExtras().getInt("count");
        address = intent.getExtras().getString("address");
        startTime = intent.getExtras().getString("startTime");
        endTime = intent.getExtras().getString("endTime");
        memo = intent.getExtras().getString("memo");
        dataString = intent.getExtras().getString("dataString");
    }

    public void insertData(Intent intent3){
        intent3.putExtra("hasData", "yes");
        intent3.putExtra("yearToDetail", year);
        intent3.putExtra("dataStringToDetail", dataString);
        intent3.putExtra("countToDetail", count);
        intent3.putExtra("addressToDetail", address);
        intent3.putExtra("startTimeToDetail", startTime);
        intent3.putExtra("endTimeToDetail", endTime);
        intent3.putExtra("memoToDetail", memo);
    }
}