package com.example.projecthensel.Recycler;

public class DateDetail {

    private String address;
    private String startTime;
    private String endTime;
    private String memo;

    public DateDetail(String address, String startTime, String endTime, String memo){
        this.address = address;
        this.startTime = startTime;
        this.endTime = endTime;
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


    public String getMemo(){return memo;}

    public void setMemo(String memo){this.memo = memo;}
}
