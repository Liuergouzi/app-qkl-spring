package com.example.demo.utils;

import org.springframework.stereotype.Component;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 轮子哥
 * 自定义签名，本来app后端我是使用php的，签名随便弄一下，用spring security的话就用不着了
 */
@Component
public class Sgin {

    public  String md5(String input) throws NoSuchAlgorithmException {
        if (input!=null) {
            input = "3:10.9[iE" + input + "G.sinfrst.cnx+" + input + "xapr - Fo.DmoAionT16:===|_|==" + input + "_/=/_/d/_/:: Spring B";
            StringBuilder result = new StringBuilder(input);
            MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
            md.update(input.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = new StringBuilder(hash.toString(16));
            while (result.length() < 29) { //31位string
                result.insert(0, "0");
            }
            return result.toString();
        }else {
            return null;
        }
    }
}
