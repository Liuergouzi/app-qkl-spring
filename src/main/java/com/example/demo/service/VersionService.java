package com.example.demo.service;

import com.example.demo.entity.Version;
import com.example.demo.mapper.VersionMapper;
import com.example.demo.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class VersionService {

    private final VersionMapper versionMapper;
    private final JsonUtil jsonUtil;

    @Autowired
    public VersionService(VersionMapper versionMapper, JsonUtil jsonUtil) {
        this.versionMapper = versionMapper;
        this.jsonUtil = jsonUtil;
    }


    public Map<String, Object> SelectVersionService() {
        Version versions = versionMapper.SelectVersionMapper();
        return jsonUtil.successReturns(versions);
    }


}