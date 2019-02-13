package com.example.vhisyhnu.iaccomm.Student.Complaint;

public class StudentDetails {

    private String mDate;
    private String mTime;
    private String mName;
    private String mID;
    private String mRoomNumber;
    private String mPhoneNumber;
    private String mComplain;
    private String mDesc;
    private String mMonth;
    private String mkey;

    public StudentDetails(){

    }

    public StudentDetails(String Date,String Time, String ID, String Name, String RoomNumber, String PhoneNumber, String Month, String Complain, String Desc) {

        mDate = Date;
        mTime = Time;
        mID = ID;
        mName = Name;
        mDesc = Desc;
        mMonth = Month;
        mRoomNumber = RoomNumber;
        mPhoneNumber=PhoneNumber;
        mComplain = Complain;
    }


    public String getDate() {
        return mDate;
    }

    public void setDate(String Date) {
        mDate = Date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String Time) {
        mTime = Time;
    }
    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String Name) {
        mName = Name;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String Desc) {
        mDesc = Desc;
    }

    public String getMonth() {
        return mMonth;
    }

    public void setMonth(String Month) {
        mMonth = Month;
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

    public String getComplain() {
        return mComplain;
    }

    public void setComplain(String Complain) {
        mComplain = Complain;
    }

    public String getKey()   {
        return mkey;
    }

    public void setKey(String key) {
        mkey = key;
    }
}
