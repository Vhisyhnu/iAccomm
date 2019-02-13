package com.example.vhisyhnu.iaccomm.Student.Complaint;

public class cBlog {
    private String studentNum;
    private String studentHostel;
    private String studentRoom;
    private String studentComplain;
    private String studentDescription;

    public cBlog(String studentNum, String studentHostel, String studentRoom,String studentComplain, String studentDescription) {
        this.studentNum = studentNum;
        this.studentHostel = studentHostel;
        this.studentRoom= studentRoom;
        this.studentComplain = studentComplain;
        this.studentDescription = studentDescription;
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

    public String getStudentComplain() {
        return studentComplain;
    }

    public void setStudentComplain(String studentComplain) {
        this.studentComplain = studentComplain;
    }

    public String getStudentDescription() {
        return studentDescription;
    }

    public void setStudentDescription(String studentDescription) {
        this.studentDescription = studentDescription;
    }



    public cBlog(){

    }


}
