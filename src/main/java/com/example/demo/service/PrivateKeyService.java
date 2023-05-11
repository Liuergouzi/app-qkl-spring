package com.example.demo.service;

import com.example.demo.entity.PrivateKey;
import com.example.demo.mapper.PrivateKeyMapper;
import com.example.demo.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrivateKeyService {

    private final PrivateKeyMapper privateKeyMapper;
    private final JsonUtil jsonUtil;
    private final RedisTemplate<String, PrivateKey> redisTemplate;

    @Autowired
    public PrivateKeyService(PrivateKeyMapper privateKeyMapper, JsonUtil jsonUtil, RedisTemplate<String, PrivateKey> redisTemplate) {
        this.privateKeyMapper = privateKeyMapper;
        this.jsonUtil = jsonUtil;
        this.redisTemplate = redisTemplate;
    }


    public Map<String, Object> PrivateKeyService(String name) {
        PrivateKey privateKeyRedis = redisTemplate.opsForValue().get("privateKey");
        if (privateKeyRedis != null) {
            return jsonUtil.successReturns(privateKeyRedis);
        } else {
            PrivateKey privateKey = privateKeyMapper.SelectKeyMapper(name);
            redisTemplate.opsForValue().set("privateKey", privateKey);
            return jsonUtil.successReturns(privateKey);
        }
    }


}
