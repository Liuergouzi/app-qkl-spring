package com.example.demo.entity;


import org.springframework.stereotype.Component;

/**
 * 作者：轮子哥
 * 版本实体类
 */

@Component
public class Version {
    private String versionCode;
    private String choice;
    private String log;
    private String url;

    public Version() {
    }

    public Version(String versionCode, String choice, String log, String url) {
        this.versionCode = versionCode;
        this.choice = choice;
        this.log = log;
        this.url = url;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
