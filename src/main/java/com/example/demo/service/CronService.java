package com.example.demo.service;


import com.example.demo.mapper.CronMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CronService {

    private final CronMapper cronMapper;

    @Autowired
    public CronService(CronMapper cronMapper) {
        this.cronMapper = cronMapper;
    }


    public String getTime(){
        return  cronMapper.getCron().getTime();
    }

    public String getMessage(){
        return  cronMapper.getCron().getMessage();
    }

}