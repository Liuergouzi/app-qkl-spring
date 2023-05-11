package com.example.demo.service;

import com.example.demo.entity.Pay;
import com.example.demo.mapper.PayMapper;
import com.example.demo.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PayService {

    private final PayMapper payMapper;
    private final JsonUtil jsonUtil;

    @Autowired
    public PayService(PayMapper payMapper, JsonUtil jsonUtil) {
        this.payMapper = payMapper;
        this.jsonUtil = jsonUtil;
    }

    public Map<String, Object> SelectPayResultService(String account, String mode) {
        List<Pay> pays = payMapper.SelectPayResult(account, mode);
        if (pays.size() >= 1) {
            return jsonUtil.successReturns("支付成功");
        } else {
            return jsonUtil.falseReturns("未支付");
        }
    }

}
