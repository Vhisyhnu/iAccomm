package com.example.vhisyhnu.iaccomm;

public class Announcement {

    String staffId;
    String staffSubject;
    String staffAnnouncement;


    public Announcement(){

    }

    public Announcement(String staffId, String staffSubject, String staffAnnouncement){
        this.staffId = staffId;
        this.staffSubject = staffSubject;
        this.staffAnnouncement = staffAnnouncement;
    }

    public String getStaffId(){

        return staffId;
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


}
