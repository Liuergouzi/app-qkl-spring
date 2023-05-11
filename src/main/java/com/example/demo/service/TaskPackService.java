package com.example.demo.service;


import com.example.demo.entity.TaskPack;
import com.example.demo.entity.User;
import com.example.demo.mapper.TaskPackMapper;
import com.example.demo.utils.JsonUtil;
import com.example.demo.utils.NowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskPackService {

    private final TaskPackMapper taskPackMapper;
    private final JsonUtil jsonUtil;

    @Autowired
    public TaskPackService(TaskPackMapper taskPackMapper, JsonUtil jsonUtil) {
        this.taskPackMapper = taskPackMapper;
        this.jsonUtil = jsonUtil;
    }


    /**
     * 轮子哥
     * 兑换任务
     */
    public Map<String, Object> InsertTask(String account, Integer type, Integer surplus, String quota) {
        Integer insert_count = taskPackMapper.TaskPackInsertMapper(account, type, surplus, quota, 30);
        if (insert_count == 1) {
            return jsonUtil.successReturns("兑换成功");
        } else {
            return jsonUtil.falseReturns("兑换失败");
        }
    }

    /**
     * 轮子哥
     * 所有任务计算
     */
    public Map<String, Object> TaskPackTotal(String account) {
        List<TaskPack> taskPacks = taskPackMapper.TaskPackTotalMapper(account);
        if (taskPacks.size() > 0) {
            Map<String, Object> result = new HashMap<>();
            BigDecimal allTotal = new BigDecimal(0);
            BigDecimal dayTotal = new BigDecimal(0);
            int index = 0;
            for (TaskPack taskPack : taskPacks) {
                allTotal = allTotal.add(new BigDecimal(taskPack.getQuota()).multiply(new BigDecimal(taskPack.getFrequency())));
                dayTotal = dayTotal.add(new BigDecimal(taskPack.getQuota()));
                index = index + 1;
            }
            result.put("allTotal", allTotal);
            result.put("dayTotal", dayTotal);
            result.put("total", index);
            return jsonUtil.successReturns(result);
        } else {
            return jsonUtil.falseReturns("当前没有任务");
        }
    }


    /**
     * 轮子哥
     * 完成任务领取
     */
    public Map<String, Object> TaskPackReceive(String account) {
        List<TaskPack> taskPacks = taskPackMapper.TaskPackTotalMapper(account);
        if (taskPacks.size() > 0) {
            if (!taskPacks.get(0).getTime().substring(0, 10).equals(new NowTime().time().substring(0, 10))) {
                Integer update_count1 = taskPackMapper.UpdateTimeMapper(account, new NowTime().time());
                Integer update_count2 = taskPackMapper.UpdateFrequencyMapper(account);
                if (update_count1 >= 1 && update_count2 >= 1) {
                    return jsonUtil.successReturns("更新成功");
                } else {
                    return jsonUtil.falseReturns("更新失败");
                }
            } else {
                return jsonUtil.falseReturns("领取失败，今日已领取");
            }
        } else {
            return jsonUtil.falseReturns("领取失败，当前没有任务");
        }
    }


}
