package com.example.demo.entity;


import org.springframework.stereotype.Component;

/**
 * 作者：轮子哥
 * 任务实体类
 */

@Component
public class TaskPack {
    private String account;
    private Integer type;
    private Integer surplus;
    private String time;
    private String quota;
    private Integer frequency;

    public TaskPack() {
    }

    public TaskPack(String account, Integer type, Integer surplus, String time, String quota, Integer frequency) {
        this.account = account;
        this.type = type;
        this.surplus = surplus;
        this.time = time;
        this.quota = quota;
        this.frequency = frequency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
