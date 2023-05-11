package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.Map;

/**
 * service接口
 */
public interface LoginServcie {
    Map<String, Object> login(User user);

    Map<String, Object> logout();

}

