package com.example.projecthensel;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity
public class RoomEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    //add 페이지에서 데이터를 받아와 저정할 변수
    public int id;
    public String year, month, date, dataString, memo, address, startTime, endTime;

    @Ignore Boolean isCheck = true;
    public RoomEntity(String year, String month, String date, String dataString, String memo,
                        String address, String startTime, String endTime){
        this.year = year;
        this.month = month;
        this.date = date;
        this.dataString = dataString;
        this.memo = memo;
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
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

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }
}
