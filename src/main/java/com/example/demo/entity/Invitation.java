package com.example.demo.entity;


import org.springframework.stereotype.Component;

@Component
public class Invitation {
    private String name;
    private String head;
    private String ma;
    private String dateTime;

    public Invitation() {
    }

    public Invitation(String name, String head, String ma, String dateTime) {
        this.name = name;
        this.head = head;
        this.ma = ma;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
