package com.example.demo.service;


import com.example.demo.entity.Notice;
import com.example.demo.mapper.NoticeMapper;
import com.example.demo.utils.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;
    private final RedisTemplate<String, Notice> redisTemplate;
    private final JsonUtil jsonUtil;

    @Autowired
    public NoticeService(NoticeMapper noticeMapper, RedisTemplate<String, Notice> redisTemplate, JsonUtil jsonUtil) {
        this.noticeMapper = noticeMapper;
        this.redisTemplate = redisTemplate;
        this.jsonUtil = jsonUtil;
    }


    public PageInfo<Notice> SelectNotice(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        PageHelper.orderBy("id desc");

        List<Notice> range = redisTemplate.opsForList().range("notice", 0, -1);
        if (range != null && !range.isEmpty()){
            return new PageInfo<>(range);
        }else {
            List<Notice> notices = noticeMapper.SelectNoticeMapper();
            for (Notice notice:notices) {
                redisTemplate.opsForList().rightPush("notice",notice);
            }
            return new PageInfo<>(notices);
        }

    }
}
