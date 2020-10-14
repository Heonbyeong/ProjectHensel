package com.example.projecthensel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton ImageButton;
    DateAdapter adapter;
    Intent intent = getIntent();
    String month;
    String date;
    int count, bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int code = 1001; //add Route 수신코드
        ImageButton = (ImageButton)findViewById(R.id.editImageButton);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bool = intent.getExtras().getInt("bool");
        if(bool == 1){
            month = intent.getExtras().getString("month");
            date = intent.getExtras().getString("date");
            count = intent.getExtras().getInt("count");
            adapter.addItem(new Data(month + "월 " + date + "일", count));
            recyclerView.setAdapter(adapter);
        }

        ImageButton.setOnClickListener(new View.OnClickListener(){     //add Route 페이지로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RouteAddingActivity.class);
                startActivityForResult(intent, code);
            }
        });
    }
}