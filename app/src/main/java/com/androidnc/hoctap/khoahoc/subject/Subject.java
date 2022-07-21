package com.androidnc.hoctap.khoahoc.subject;

public class Subject {
    //cac bieen khoa hoc

    // id khoa hoc
    private int id;
    //id ten khoa hoc &&  mon hoc
    private String subject_title;
    //so tin chi
    private int number_of_credit;
    //thoi gian hoc && thoi gin dang ki khoa hoc
    private String time;
    //dia diem && ghi chu
    private String place;


    // Dùng để cập nhật
    public Subject(String subject_title, int number_of_credit, String time, String place) {
        this.subject_title = subject_title;
        this.number_of_credit = number_of_credit;
        this.time = time;
        this.place = place;
    }

    public Subject(int id, String subject_title, int number_of_credit, String time, String place) {
        this.id = id;
        this.subject_title = subject_title;
        this.number_of_credit = number_of_credit;
        this.time = time;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public int getNumber_of_credit() {
        return number_of_credit;
    }

    public void setNumber_of_credit(int number_of_credit) {
        this.number_of_credit = number_of_credit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
