package com.example.projecthensel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import androidx.room.Room;

import com.example.projecthensel.Room.AppDatabase;

public class RouteAddingActivity extends AppCompatActivity {

    Button returnButton, addButton, searchButton;
    TextView countText;
    EditText addressEdit, yearEdit, monthEdit, dateEdit, hourStartEdit, minStartEdit, hourEndEdit, minEndEdit, memoEdit;
    WebView daum_webView;
    String year, month, date, fullDate, memo,address, startTime, endTime;
    InputMethodManager imm;
    ConstraintLayout mainLayout;
    private Handler handler;
    private Parcelable recyclerViewState = null;
    private AppDatabase db;

    int count = 0;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_adding_page);
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

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);   //추가 하기전 다시 알리는 객체

        //EditText에 담긴 내용을 추가하고 전달하기 위한 코드
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
                            Intent intent = new Intent(RouteAddingActivity.this, MainActivity.class);
                            getEdit();
                            //db.dateDao().update(year, month, date, dataString, memo, address, startTime, endTime, id);
                            insertData(intent);
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

        //주소 검색 API
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                init_webView();

                daum_webView.setWebChromeClient(new WebChromeClient(){
                    @SuppressLint("SetJavaScriptEnabled")
                    @Override
                    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg){
                        WebView newWebView = new WebView(getApplicationContext());
                        WebSettings webSettings = newWebView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        final Dialog dialog = new Dialog(getApplicationContext());
                        dialog.setContentView(newWebView);
                        dialog.show();
                        newWebView.setWebChromeClient(new WebChromeClient(){
                            @Override
                            public void onCloseWindow(WebView window){
                                dialog.dismiss();
                            }
                        });
                        ((WebView.WebViewTransport)resultMsg.obj).setWebView(newWebView);
                        resultMsg.sendToTarget();

                        return true;
                    }
                });

                handler = new Handler();
            }
        });

        //배경 클릭시 키보드가 내려가게 하는 코드
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
                Intent intent = new Intent(RouteAddingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public void init_webView(){
        daum_webView = findViewById(R.id.daum_webView);
        daum_webView.getSettings().setJavaScriptEnabled(true);
        daum_webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        daum_webView.addJavascriptInterface(new AndroidBridge(), "kakaoAPI");
        //daum_webView.setWebChromeClient(chromeClient);
        daum_webView.loadUrl("file:///android_asset/test_doc.html");
    }

    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final  String arg1, final  String arg2, final  String arg3){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    addressEdit.setText(String.format("(%s) %s %s", arg1, arg2, arg3));

                    init_webView();
                }
            });
        }
    }

    //데이터를 Main 페이지로 전달
    public void insertData(Intent intent){
        intent.putExtra("bool", true);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        intent.putExtra("date", date);
        intent.putExtra("fullDate", fullDate);
        intent.putExtra("address", address);
        intent.putExtra("startTime", startTime);
        intent.putExtra("endTime", endTime);
        intent.putExtra("memo", memo);
        intent.putExtra("count", count);
    }

    //EditText 초기화
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

    //EditText에 담긴 문자열을 변수에 저장
    public void getEdit(){
        year = yearEdit.getText().toString();
        month = monthEdit.getText().toString();
        date = dateEdit.getText().toString();
        address = addressEdit.getText().toString();
        startTime = hourStartEdit.getText().toString() + ":" + minStartEdit.getText().toString();
        endTime = hourEndEdit.getText().toString() + ":" + minEndEdit.getText().toString();
        memo = memoEdit.getText().toString();
        fullDate = month + "월 " + date + "일";
        count += 1;
    }
}



