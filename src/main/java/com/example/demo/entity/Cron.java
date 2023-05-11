package com.example.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class Cron {

    private Integer id;
    private String time;
    private String message;


    public Cron() {
    }

    public Cron(Integer id, String time, String message) {
        this.id = id;
        this.time = time;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}