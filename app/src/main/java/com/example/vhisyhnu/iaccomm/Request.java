package com.example.vhisyhnu.iaccomm;

public class Request {

    String studentId;
    String studentNum;
    String studentHostel;
    String studentRoom;
    String studentRequest;

    public Request(){

    }

    public Request(String studentId, String studentNum, String studentHostel, String studentRoom, String studentRequest){
        this.studentId = studentId;
        this.studentNum = studentNum;
        this.studentHostel = studentHostel;
        this.studentRoom= studentRoom;
        this.studentRequest = studentRequest;
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

    public String getStudentRequest(){

        return studentRequest;
    }

    public void setStudentRequest(String studentRequest){

        this.studentRequest = studentRequest;
    }

}
