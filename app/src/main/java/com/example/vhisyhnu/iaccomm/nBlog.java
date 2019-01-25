package com.example.vhisyhnu.iaccomm;

public class nBlog {
    private String studentNum;
    private String studentHostel;
    private String studentRoom;
    private String studentNotify;

    public nBlog(String studentNum, String studentHostel, String studentRoom,String studentNotify) {
        this.studentNum = studentNum;
        this.studentHostel = studentHostel;
        this.studentRoom= studentRoom;
        this.studentNotify = studentNotify;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentHostel() {
        return studentHostel;
    }

    public void setStudentHostel(String studentHostel) {
        this.studentHostel = studentHostel;
    }

    public String getStudentRoom() {
        return studentRoom;
    }

    public void setStudentRoom(String studentRoom) {
        this.studentRoom = studentRoom;
    }

    public String getStudentNotify() {
        return studentNotify;
    }

    public void setStudentNotify(String studentNotify) {
        this.studentNotify = studentNotify;
    }



    public nBlog(){

    }


}
