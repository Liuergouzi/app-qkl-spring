package com.example.demo.entity;

import org.springframework.stereotype.Component;

/**
 * 作者：轮子哥
 * 图文实体类
 */

@Component
public class Article {
    private Integer id;
    private String account;
    private String content;
    private String time;
    private Integer imageSize;
    private String name;
    private String head;
    private Integer commentCount;
    private Integer loveCount;

    public Article() {
    }

    public Article(Integer id, String account, String content, String time, Integer imageSize, String name, String head, Integer commentCount, Integer loveCount) {
        this.id = id;
        this.account = account;
        this.content = content;
        this.time = time;
        this.imageSize = imageSize;
        this.name = name;
        this.head = head;
        this.commentCount = commentCount;
        this.loveCount = loveCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
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

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(Integer loveCount) {
        this.loveCount = loveCount;
    }
}
