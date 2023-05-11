package com.example.demo.entity;


import org.springframework.stereotype.Component;

/**
 * 作者：轮子哥
 * 用户实体类
 */

@Component
public class User {
    private String account;
    private String password;
    private String payPassword;
    private String name;
    private String head;
    private String address;
    private String phone;
    private String cardName;
    private String cardId;
    private String invitation;
    private String ma;
    private String dataTime;
    private String balance;

    public User() {
    }

    public User(String account, String password, String payPassword, String name, String head, String address, String phone, String cardName, String cardId, String invitation, String ma, String dataTime, String balance) {
        this.account = account;
        this.password = password;
        this.payPassword = payPassword;
        this.name = name;
        this.head = head;
        this.address = address;
        this.phone = phone;
        this.cardName = cardName;
        this.cardId = cardId;
        this.invitation = invitation;
        this.ma = ma;
        this.dataTime = dataTime;
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getInvitation() {
        return invitation;
    }

    public void setInvitation(String invitation) {
        this.invitation = invitation;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
