package com.example.vhisyhnu.iaccomm;

public class DateModel {

    public String date;
    public String time;
    public String month;
    private String mkey;

    public DateModel() {

    }

    public DateModel(String date, String time, String month) {
        this.date = date;
        this.time = time;
        this.month = month;
    }

    public String getKey() {
        return mkey;
    }

    public void setKey(String key) {
        mkey = key;
    }

}
