package com.example.vhisyhnu.iaccomm.Student.Request;


public class Request {

    public String date;
    public String time;
    public String studentRequest;
    private String mName;
    private String mID;
    private String mRoomNumber;
    private String mPhoneNumber;

    public Request() {

    }



    public Request(String date, String time, String studentRequest, String Name, String ID, String RoomNumber, String PhoneNumber){
        this.date = date;
        this.time = time;
        this.studentRequest = studentRequest;
        mName = Name;
        mID = ID;
        mRoomNumber = RoomNumber;
        mPhoneNumber=PhoneNumber;
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
