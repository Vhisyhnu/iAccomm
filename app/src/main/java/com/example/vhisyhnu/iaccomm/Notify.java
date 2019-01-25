package com.example.vhisyhnu.iaccomm;

public class Notify {

    String studentId;
    String studentNum;
    String studentHostel;
    String studentRoom;
    String studentNotify;

    public Notify(){

    }

    public Notify(String studentId, String studentNum, String studentHostel, String studentRoom, String studentNotify){
        this.studentId = studentId;
        this.studentNum = studentNum;
        this.studentHostel = studentHostel;
        this.studentRoom= studentRoom;
        this.studentNotify = studentNotify;
    }

    public String getStudentId(){

        return studentId;
    }

    public String getStudentNum(){

        return studentNum;
    }

    public void setStudentNum(String studentNum){

        this.studentNum = studentNum;
    }

    public String getStudentHostel(){

        return studentHostel;
    }
    public void setStudentHostel(String studentHostel){

        this.studentHostel = studentHostel;
    }

    public String getStudentRoom(){

        return studentRoom;
    }

    public void setStudentRoom(String studentRoom){

        this.studentRoom = studentRoom;
    }

    public String getStudentNotify(){

        return studentNotify;
    }

    public void setStudentNotify(String studentNotify){

        this.studentNotify = studentNotify;
    }

}
