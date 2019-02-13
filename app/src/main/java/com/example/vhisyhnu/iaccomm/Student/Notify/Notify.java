package com.example.vhisyhnu.iaccomm.Student.Notify;

import com.google.firebase.database.Exclude;

public class    Notify {

    public String date;
    public String time;
    public String studentNotify;
    private String mName;
    private String mID;
    private String mRoomNumber;
    private String mPhoneNumber;
    private String mHostel;
    private String key;
    private String imageURL;

    public Notify() {

    }



    public Notify(String studentNotify, String imageUrl){
        this.date = date;
        this.time = time;
        this.studentNotify = studentNotify;
       // mName = Name;
        //mID = ID;
       // mRoomNumber = RoomNumber;
        //mPhoneNumber=PhoneNumber;
       // mHostel=Hostel;
        this.imageURL = imageUrl;
    }

    /*public String getName() {
        return mName;
    }

    public void setName(String Name) {
        mName = Name;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getHostel() {
        return mHostel;
    }

    public void setHostel(String Hostel) {
        mHostel = Hostel;
    }

    public String getRoomNumber() {
        return mRoomNumber;
    }

    public void setRoomNumber(String RoomNumber) {
        mRoomNumber = RoomNumber;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        mPhoneNumber = PhoneNumber;
    }*/

    public String getStudentNotify(){

        return studentNotify;
    }

    public void setStudentNotify(String studentNotify){

        this.studentNotify = studentNotify;
    }
    public String getImageUrl() {
        return imageURL;
    }
    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }
    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

}
