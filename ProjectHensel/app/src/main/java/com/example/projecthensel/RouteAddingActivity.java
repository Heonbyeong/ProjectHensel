package com.example.projecthensel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RouteAddingActivity extends AppCompatActivity {

    Button returnButton, addButton, searchButton;
    TextView countText;
    EditText addressEdit, yearEdit, monthEdit, dateEdit, hourStartEdit, minStartEdit, hourEndEdit, minEndEdit, memoEdit;
    WebView daum_webView;
    String year, month, date, memo,address, startHour, startMin, endHour, endMin;
    InputMethodManager imm;
    ConstraintLayout mainLayout;
    int count = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_adding_page);
        final int code2 = 1002;
        returnButton = (Button) findViewById(R.id.addToAllButton);
        addButton = (Button) findViewById(R.id.addButton);
        searchButton = (Button) findViewById(R.id.searchButton);
        countText = (TextView)findViewById(R.id.countText);
        addressEdit = (EditText) findViewById(R.id.addressEdit);
        yearEdit = (EditText)findViewById(R.id.yearEdit);
        monthEdit = (EditText)findViewById(R.id.monthEdit);
        dateEdit = (EditText)findViewById(R.id.dateEdit);
        hourStartEdit = (EditText)findViewById(R.id.hourStartEdit);
        minStartEdit = (EditText)findViewById(R.id.minStartEdit);
        hourEndEdit = (EditText)findViewById(R.id.hourEndEdit);
        minEndEdit = (EditText)findViewById(R.id.minEndEdit);
        memoEdit = (EditText)findViewById(R.id.memoEdit);
        mainLayout = (ConstraintLayout)findViewById(R.id.constraint);
        //기본 SharedPreferences 환경과 관련된 객체 얻기
        //pref = PreferenceManager.getDefaultSharedPreferences(this);
        //editor = pref.edit();

        //추가 하기전 다시 알리는 객체
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //배경을 누를시 키보드가 내려가게 하는 코드
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(addressEdit.getWindowToken(), 0);
            }
        });
        //index 페이지로 돌아가는 코드
        returnButton.setOnClickListener(new View.OnClickListener() { // All Route 페이지로 이동하는 인텐트
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, code2);
            }
        });
        //EditText에 입력하고 추가하기 위한 코드
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("경로를 추가하시겠습니까?");
                builder.setCancelable(false);
                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(monthEdit.getText().toString().equals("") || dateEdit.getText().toString().equals("") || addressEdit.getText().toString().equals("") ||
                                hourStartEdit.getText().toString().equals("") || minStartEdit.getText().toString().equals("") || hourEndEdit.getText().toString().equals("") ||
                                minEndEdit.getText().toString().equals("")){
                            Toast toast = Toast.makeText(getApplicationContext(), "빈칸을 채워주세요", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            year = yearEdit.getText().toString();
                            month = monthEdit.getText().toString();
                            date = dateEdit.getText().toString();
                            address = addressEdit.getText().toString();
                            startHour = hourStartEdit.getText().toString();
                            startMin = minStartEdit.getText().toString();
                            endHour = hourEndEdit.getText().toString();
                            endMin = minEndEdit.getText().toString();
                            memo = memoEdit.getText().toString();
                            count += 1;

                            Intent intent = new Intent(RouteAddingActivity.this, MainActivity.class);
                            insertData(intent);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);

                            initData();
                            finish();
                        }
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

        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }
    public void insertData(Intent intent){
        intent.putExtra("bool", "1");
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("date", date);
        intent.putExtra("address", address);
        intent.putExtra("startHour", startHour);
        intent.putExtra("startMin", startMin);
        intent.putExtra("endHour", endHour);
        intent.putExtra("endMin", endMin);
        intent.putExtra("memo", memo);
        intent.putExtra("count", count);
    }

    public void initData(){
        yearEdit.setText("");
        monthEdit.setText("");
        dateEdit.setText("");
        memoEdit.setText("");
        hourStartEdit.setText("");
        minStartEdit.setText("");
        hourEndEdit.setText("");
        minEndEdit.setText("");
        addressEdit.setText("");
    }

}
