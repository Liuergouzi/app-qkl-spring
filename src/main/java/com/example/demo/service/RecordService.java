package com.example.demo.service;


import com.example.demo.entity.Record;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.utils.JsonUtil;
import com.example.demo.utils.NowTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService {

    private final RecordMapper recordMapper;
    private final JsonUtil jsonUtil;

    @Autowired
    public RecordService(RecordMapper recordMapper, JsonUtil jsonUtil) {
        this.recordMapper = recordMapper;
        this.jsonUtil = jsonUtil;
    }


    public Map<String, Object> SelectRecord(String account,int pageNum) {
        PageHelper.startPage(pageNum, 10);
        PageHelper.orderBy("id desc");
        List<Record> record = recordMapper.SelectRecord(account);
        BigDecimal count=new BigDecimal("0");
        for (Record i:record) {
            if (i.getTime().substring(0,10).equals(new NowTime().time().substring(0,10))){
                count=count.add(new BigDecimal(i.getCount()));
            }
        }
        PageInfo<Record> recordPageInfo = new PageInfo<>(record);
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("dayBalance",count);
        result.put("total",recordPageInfo.getPages());
        result.put("data",recordPageInfo.getList());
        return result;
    }


    public Map<String, Object> RecordInsert(String account,String count,String message,String source) {
        Integer insert_count=recordMapper.RecordInsertMapper(account,count, new NowTime().time(),message,source);
        if (insert_count == 1) {
            return jsonUtil.successReturns("记录成功");
        } else {
            return jsonUtil.falseReturns("记录失败");
        }
    }
}
