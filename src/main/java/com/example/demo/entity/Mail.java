package com.example.demo.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Mail implements Serializable {

    private String QQ;
    private String title;
    private String content;
    private String imageUrl;

    public Mail() {
    }

    public Mail(String QQ, String title, String content, String imageUrl) {
        this.QQ = QQ;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
