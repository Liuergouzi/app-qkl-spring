package com.example.demo.exception;

import com.alibaba.fastjson2.JSON;
import com.example.demo.utils.JsonUtil;
import com.example.demo.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 轮子哥
 * 认证失败，自定义抛出异常处理器
 * spring security会覆盖异常，需要将异常信息存在HttpServletRequest中获取
 * authException.getMessage()
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String, Object> result = new JsonUtil().falseReturns((String) request.getAttribute("errorMessage"));
        if (request.getAttribute("errorMessage") != null) {

            if (request.getAttribute("errorMessage").equals("10001")) {
                result = new JsonUtil().falseReturns(10001, "非法请求");
                String json = JSON.toJSONString(result);
                WebUtils.renderString(response, json);
            } else if (request.getAttribute("errorMessage").equals("10002")) {
                result = new JsonUtil().falseReturns(10002, "登录已失效，请重新登录");
                String json = JSON.toJSONString(result);
                WebUtils.renderString(response, json);
            } else {
                String json = JSON.toJSONString(result);
                WebUtils.renderString(response, json);
            }
        } else {
            result = new JsonUtil().falseReturns(400, "账号或密码错误");
            String json = JSON.toJSONString(result);
            WebUtils.renderString(response, json);
        }
    }
}
