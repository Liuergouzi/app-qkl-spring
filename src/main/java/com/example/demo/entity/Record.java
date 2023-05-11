package com.example.demo.entity;


import org.springframework.stereotype.Component;

/**
 * 作者：轮子哥
 * 积分记录实体类
 */

@Component
public class Record {

    private String count;
    private String time;
    private String message;
    private String source;
    private String account;

    public Record() {
    }

    public Record(String count, String time, String message, String source, String account) {
        this.count = count;
        this.time = time;
        this.message = message;
        this.source = source;
        this.account = account;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
