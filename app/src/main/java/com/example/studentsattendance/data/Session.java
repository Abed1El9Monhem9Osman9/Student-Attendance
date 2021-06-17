package com.example.studentsattendance.data;

public class Session extends BaseClass {
    int id_session;
    String title;

    public Session( String title) {
        this.title = title;
    }

    public Session(int anInt, String string) {
        this.id_session = anInt;
        this.title = string;
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
