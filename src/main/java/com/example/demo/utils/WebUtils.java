package com.example.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils
{
    /**
     * 轮子哥，自定义异常返回输出体
     * 将字符串渲染到客户端
     */
    public static String renderString(HttpServletResponse response, String string) {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

