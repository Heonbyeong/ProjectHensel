package com.example.projecthensel.Room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity(tableName = "dateTable")
public class Date {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String year;
    private String month;
    private String day;
    private String fullDate;
    private String memo;
    private String address;
    private String startTime;
    private String endTime;
    private int count;

    public Date(String year, String month, String day, String fullDate, String memo,
                String address, String startTime, String endTime, int count){
        this.year = year;
        this.month = month;
        this.day = day;
        this.fullDate = fullDate;
        this.memo = memo;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCount() {return count;}

    public void setCount(int count) {this.count = count;}
}
