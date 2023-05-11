package com.example.demo.exception;

import com.alibaba.fastjson2.JSON;
import com.example.demo.utils.JsonUtil;
import com.example.demo.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 轮子哥
 * 403
 **/

@Component
public class AccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, Object> result = new JsonUtil().falseReturns("访问拦截" + accessDeniedException.getMessage());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}