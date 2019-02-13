package com.example.vhisyhnu.iaccomm.Admin;

public class Student {
    public String email, name, studentId, type,id;

    public Student(){

    }

    public Student(String Email, String Name,String studentId, String Type,String Id ) {
        this.email = Email;
        this.name = Name;
        this.id = Id;
        this.type = Type;
        this.studentId = studentId;

    }


    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }


    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }


}