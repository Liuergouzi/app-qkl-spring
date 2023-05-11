package com.example.demo.entity;


import org.springframework.stereotype.Component;

/**
 * 作者：轮子哥
 * 支付实体类
 */

@Component
public class Pay {

    private String oid;
    private String price;
    private String time;
    private String tradeNo;
    private String paySource;
    private String body;
    private String mode;

    public Pay() {
    }

    public Pay(String oid, String price, String time, String tradeNo, String paySource, String body, String mode) {
        this.oid = oid;
        this.price = price;
        this.time = time;
        this.tradeNo = tradeNo;
        this.paySource = paySource;
        this.body = body;
        this.mode = mode;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
