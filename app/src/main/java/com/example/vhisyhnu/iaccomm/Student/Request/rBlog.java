package com.example.vhisyhnu.iaccomm.Student.Request;

public class rBlog {

    public String date;
    public String time;
    public String studentRequest;
    private String mName;
    private String mID;
    private String mRoomNumber;
    private String mPhoneNumber;
    private String mHostel;

    public rBlog() {

    }



    public rBlog(String date, String time, String studentRequest, String Name, String ID, String RoomNumber, String PhoneNumber, String Hostel){
        this.date = date;
        this.time = time;
        this.studentRequest = studentRequest;
        mName = Name;
        mID = ID;
        mRoomNumber = RoomNumber;
        mPhoneNumber=PhoneNumber;
        mHostel=Hostel;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
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
    }

    public String getStudentRequest(){

        return studentRequest;
    }

    public void setStudentRequest(String studentRequest){

        this.studentRequest = studentRequest;
    }

}
