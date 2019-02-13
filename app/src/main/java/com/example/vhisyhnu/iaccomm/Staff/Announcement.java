package com.example.vhisyhnu.iaccomm.Staff;

import com.google.firebase.database.Exclude;

public class Announcement {


    public String date;
    public String time;
    private String key;
    String staffSubject;
    String staffAnnouncement;


    public Announcement(){

    }

    public Announcement(String date, String time,String staffSubject, String staffAnnouncement){
        this.date = date;
        this.time = time;
        this.staffSubject = staffSubject;
        this.staffAnnouncement = staffAnnouncement;

    }

    public String getTime(){

        return time;
    }
    public String getDate(){

        return date;
    }

    public String getStaffSubject(){

        return staffSubject;
    }


    public void setStaffSubject(String staffSubject){

        this.staffSubject = staffSubject;
    }

    public String getStaffAnnouncement(){

        return staffAnnouncement;
    }

    public void setStaffAnnouncement (String staffAnnouncement){

        this.staffAnnouncement = staffAnnouncement;
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
