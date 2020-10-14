package com.example.projecthensel;

public class Data {

    private String data;
    private int count;

    public Data(String data, int count){
        this.data = data;
        this.count = count;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }
}
