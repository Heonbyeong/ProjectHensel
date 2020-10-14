package com.example.projecthensel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RouteAddingActivity extends AppCompatActivity {

    Button returnButton, addButton;
    TextView countText;
    EditText addressEdit, yearEdit, monthEdit, dateEdit;
    DateAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_adding_page);
        final int code2 = 1002;
        returnButton = (Button) findViewById(R.id.allButton);
        addButton = (Button) findViewById(R.id.addButton);
        countText = (TextView)findViewById(R.id.countText);
        addressEdit = (EditText) findViewById(R.id.addressEdit);
        yearEdit = (EditText)findViewById(R.id.yearEdit);
        monthEdit = (EditText)findViewById(R.id.monthEdit);
        dateEdit = (EditText)findViewById(R.id.dateEdit);
        adapter = new DateAdapter();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        returnButton.setOnClickListener(new View.OnClickListener() { // All Route 페이지로 이동하는 인텐트
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, code2);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int count = 0;

                builder.setMessage("경로를 추가하시겠습니까?");
                builder.setCancelable(false);
                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(RouteAddingActivity.this, MainActivity.class);
                        intent.putExtra("bool", 1);
                        intent.putExtra("month", monthEdit.getText().toString());
                        intent.putExtra("date", dateEdit.getText().toString());
                        intent.putExtra("count", (count + 1));
                        startActivity(intent);

                        monthEdit.setText("");
                        dateEdit.setText("");
                        finish();
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
            }
        });

    }
}
