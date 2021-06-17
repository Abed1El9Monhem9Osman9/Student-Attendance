package com.example.studentsattendance.data;

import java.util.Date;

public class Student extends BaseClass {

    int idUser;
    int fileNumber;
    String fullName;
    String date;
    int checked;


    public Student(int fileNumber, String fullName) {
        this.fileNumber = fileNumber;
        this.fullName = fullName;
        this.date = date;
    }

    public Student(int fileNumber, String fullName, String date, int checked) {
        this.fileNumber = fileNumber;
        this.fullName = fullName;
        this.date = date;
        this.checked = checked;
    }
    public Student(int fileNumber, String fullName,int checked) {
        this.fileNumber = fileNumber;
        this.fullName = fullName;
        this.checked = checked;
    }
    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", fileNumber=" + fileNumber +
                ", fullName='" + fullName + '\'' +
                ", date='" + date + '\'' +
                ", checked=" + checked +
                '}';
    }
}
