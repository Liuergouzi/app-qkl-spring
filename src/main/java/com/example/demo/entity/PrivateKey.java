package com.example.demo.entity;


public class PrivateKey {
    private String name;
    private String keyValue;

    public PrivateKey() {
    }

    public PrivateKey(String name, String keyValue) {
        this.name = name;
        this.keyValue = keyValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getkeyValue() {
        return keyValue;
    }

    public void setkeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
