package com.example.demo.utils;


import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：轮子哥
 * 获取当前时间
 */


@Component
public class NowTime {

    public String time() {
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

}
