package com.example.projecthensel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projecthensel.Recycler.DateMain;
import com.example.projecthensel.Recycler.DateMainAdapter;
import com.example.projecthensel.Room.Date;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton ImageButton;
    DateMainAdapter adapter = new DateMainAdapter();
    Toast toast2;
    String year, month, date, fullDate; // Adding 페이지에서 받아오는 값을 저장
    String memo,address, startTime, endTime; // Detail 페이지로 넘길 값을 저장
    Boolean bool;
    private long backKeyPressedTime = 0; // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private List<Date> dateList;
    int count;
    Parcelable recyclerViewState = null;
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
        if(recyclerViewState != null)
            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        if(intent.hasExtra("bool"))
       {
           //add Route 페이지에서 데이터 받아오기
           getData(intent);
           adapter.addItem(new DateMain(fullDate, count));
           //Main -> Detail 페이지로 데이터 전달
           insertData(intent3);

           intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
           startActivity(intent3);
        }

        //이미지 버튼 클릭시 Add 페이지로 이동
            ImageButton.setOnClickListener(new View.OnClickListener(){     //add Route 페이지로 이동
            @Override
            public void onClick(View v) {
                saveRecyclerViewState();
                Intent intent2 = new Intent(MainActivity.this, RouteAddingActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
//                finish();
            }
        });
    }
//    protected void onStart() {
//        dateList = AppDatabase.getInstance(this).dateDao().getAll();
//        adapter.addItem((ArrayList) dateList);
//        super.onStart();
//    }

    @Override
    public void onResume(){
        super.onResume();
        if(recyclerViewState != null){
            setSavedRecyclerViewState();
        }
    }

//    private void openAnotherActivity(){
//        saveRecyclerViewState();
//        Intent intent = new Intent(this, DetailRouteActivity.class);
//        startActivity(intent);
//    }

    private void saveRecyclerViewState() {
        // LayoutManager를 불러와 Parcelable 변수에 리사이클러뷰 상태를 Bundle 형태로 저장
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
    }

    private void setSavedRecyclerViewState(){
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
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
        fullDate = intent.getExtras().getString("fullDate");
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