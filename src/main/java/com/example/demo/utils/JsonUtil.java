package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 作者：轮子哥
 * 返回体
 */

@Component
public class JsonUtil {


    /**
     *轮子哥
     * 定制返回体
     */

    public Map<String, Object> returns(String key, String content, int length){
        Map<String, Object> result = new HashMap<>();
        if (length>0) {
            try {
                String[] keys = key.split(",");
                String[] contents = content.split(",");
                for (int i = 0; i < length; i++) {
                    result.put(keys[i], contents[i]);
                }
                return result;
            } catch (Exception e) {
                System.out.println("道友莫要开玩笑！");
                e.printStackTrace();
                result.put("code", 400);
                result.put("message", "返回体数据格式错误");
                return result;
            }
        }else {
            System.out.println("道友莫要开玩笑！");
            result.put("code",400);
            result.put("message","返回体数据格式错误");
            return result;
        }
    }


    /**
     *轮子哥
     * 成功请求返回
     */
    public Map<String, Object> successReturns(String key,String value){
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","请求成功");
        result.put(key,value);
        return result;
    }

    public Map<String, Object> successReturns(Object object){
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","请求成功");
        result.put("data",object);
        return result;
    }

    public Map<String, Object> successReturns(String message){
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message",message);
        return result;
    }

    public Map<String, Object> successReturns(){
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","请求成功");
        return result;
    }


    /**
     *轮子哥
     * 失败请求返回
     */

    public Map<String, Object> falseReturns(Integer code,String message){
        Map<String, Object> result = new HashMap<>();
        result.put("code",code);
        result.put("message",message);
        result.put("data","");
        return result;
    }

    public Map<String, Object> falseReturns(String message){
        Map<String, Object> result = new HashMap<>();
        result.put("code",400);
        result.put("message",message);
        return result;
    }

    public Map<String, Object> falseReturns(){
        Map<String, Object> result = new HashMap<>();
        result.put("code",400);
        result.put("message","请求失败");
        return result;
    }

}
