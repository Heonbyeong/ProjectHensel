package com.example.projecthensel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projecthensel.Recycler.DateMain;
import com.example.projecthensel.Recycler.MainDateAdapter;
import com.example.projecthensel.Room.AppDatabase;
import com.example.projecthensel.Room.Date;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton ImageButton;
    MainDateAdapter adapter;
    Toast toast2;
    String year, month, day, fullDate; // Adding 페이지에서 받아오는 값을 저장
    String memo,address, startTime, endTime; // Detail 페이지로 넘길 값을 저장
    Boolean bool;
    int count;

    private AppDatabase db;

    private long backKeyPressedTime = 0; // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private List<Date> dateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton = findViewById(R.id.editImageButton);
        recyclerView = findViewById(R.id.recyclerView);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);

        final Intent intent = getIntent();
        Intent intent3 = new Intent(getApplicationContext(), DetailRouteActivity.class);
        if(intent.hasExtra("bool"))
       {
           //add Route 페이지에서 데이터 받아오기
           bool = intent.getExtras().getBoolean("bool");
           year = intent.getExtras().getString("year");
           month = intent.getExtras().getString("month");
           day = intent.getExtras().getString("day");
           count = intent.getExtras().getInt("count");
           address = intent.getExtras().getString("address");
           startTime = intent.getExtras().getString("startTime");
           endTime = intent.getExtras().getString("endTime");
           memo = intent.getExtras().getString("memo");
           fullDate = intent.getExtras().getString("fullDate");

           //DB추가
           new Thread(() ->{
               Date date = new Date(year, month, day, fullDate, memo, address, startTime, endTime, count);
               db.dateDao().insert(date);
           }).start();

           db = AppDatabase.getDatabase(this);
           recyclerView.setHasFixedSize(true);
           RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
           recyclerView.setLayoutManager(layoutManager);
           adapter = new MainDateAdapter(db);
           recyclerView.setAdapter(adapter);

           //UI 갱신 (Observer 이용, DB 값이 변화가 생기면 실행)
           db.dateDao().getAll().observe(this, new Observer<List<Date>>() {
               @Override
               public void onChanged(List<Date> data) {
                   adapter.setItem(data);
               }
           });

           //Main -> Detail 페이지로 데이터 전달
           insertData(intent3);

           //intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
           startActivity(intent3);
        }

        //이미지 버튼 클릭시 Add 페이지로 이동
            ImageButton.setOnClickListener(new View.OnClickListener(){     //add Route 페이지로 이동
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, RouteAddingActivity.class);
                //intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
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

    }

    public void insertData(Intent intent3){
        intent3.putExtra("hasData", "yes");
        intent3.putExtra("yearToDetail", year);
        intent3.putExtra("fullDateToDetail", fullDate);
        intent3.putExtra("countToDetail", count);
        intent3.putExtra("addressToDetail", address);
        intent3.putExtra("startTimeToDetail", startTime);
        intent3.putExtra("endTimeToDetail", endTime);
        intent3.putExtra("memoToDetail", memo);
    }
}