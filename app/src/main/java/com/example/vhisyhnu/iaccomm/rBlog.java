package com.example.vhisyhnu.iaccomm;

public class rBlog {
    private String studentNum;
    private String studentHostel;
    private String studentRoom;
    private String studentRequest;

    public rBlog(String studentNum, String studentHostel, String studentRoom,String studentNotify) {
        this.studentNum = studentNum;
        this.studentHostel = studentHostel;
        this.studentRoom= studentRoom;
        this.studentRequest = studentRequest;
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

    public String getStudentRequest() {
        return studentRequest;
    }

    public void setSstudentRequest(String studentRequest) {
        this.studentRequest = studentRequest;
    }



    public rBlog(){

    }


}
