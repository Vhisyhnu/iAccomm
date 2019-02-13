package com.example.vhisyhnu.iaccomm.Student.Complaint;

public class Complain {

    public String Complain;
    public String Desc;
    private String mkey;

    public Complain(){

    }

    public Complain(String Complain, String Desc) {
        this.Complain = Complain;
        this.Desc = Desc;
    }

    public String getKey() {
        return mkey;
    }

    public void setKey(String key) {
        mkey = key;
    }


}
