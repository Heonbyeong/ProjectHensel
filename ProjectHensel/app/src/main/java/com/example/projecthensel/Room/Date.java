package com.example.projecthensel.Room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.io.Serializable;

@Entity(tableName = "dateTable")
public class Date implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "date_Id")
    private int id;
    @ColumnInfo(name = "date_year")
    private String year;
    @ColumnInfo(name = "date_month")
    private String month;
    @ColumnInfo(name = "date_date")
    private String date;
    @ColumnInfo(name = "date_dataString")
    private String dataString;
    @ColumnInfo(name = "date_memo")
    private String memo;
    @ColumnInfo(name = "date_address")
    private String address;
    @ColumnInfo(name = "date_startTime")
    private String startTime;
    @ColumnInfo(name = "date_endTime")
    private String endTime;

    @Ignore Boolean isCheck = true;
    public Date(String year, String month, String date, String dataString, String memo,
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

    protected Date(Parcel in) {
        id = in.readInt();
        year = in.readString();
        month = in.readString();
        date = in.readString();
        dataString = in.readString();
        memo = in.readString();
        address = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        byte tmpIsCheck = in.readByte();
        isCheck = tmpIsCheck == 0 ? null : tmpIsCheck == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(year);
        dest.writeString(month);
        dest.writeString(date);
        dest.writeString(dataString);
        dest.writeString(memo);
        dest.writeString(address);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeByte((byte) (isCheck == null ? 0 : isCheck ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Date> CREATOR = new Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel in) {
            return new Date(in);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };

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
