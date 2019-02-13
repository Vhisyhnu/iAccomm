package com.example.vhisyhnu.iaccomm.Staff;

public class Blog {
    private String staffSubject;
    private String staffAnnouncement;

    public Blog(String staffSubject, String staffAnnouncement) {
        this.staffSubject = staffSubject;
        this.staffAnnouncement = staffAnnouncement;
    }

    public String getStaffSubject() {
        return staffSubject;
    }

    public void setStaffSubject(String staffSubject) {
        this.staffSubject = staffSubject;
    }

    public String getStaffAnnouncement() {
        return staffAnnouncement;
    }

    public void setStaffAnnouncement(String staffAnnouncement) {
        this.staffAnnouncement = staffAnnouncement;
    }

    public Blog(){

    }


}
