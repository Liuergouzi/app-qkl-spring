package com.example.demo.service.impl;

import com.example.demo.entity.LoginUser;
import com.example.demo.entity.RedisUser;
import com.example.demo.entity.User;
import com.example.demo.service.LoginServcie;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginUserImpl implements LoginServcie {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, RedisUser> redisTemplate;

    @Autowired
    public LoginUserImpl(UserService userService, AuthenticationManager authenticationManager, RedisTemplate<String, RedisUser> redisTemplate) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Map<String, Object> login(User user) {
        // AuthenticationManager的authenticate方法进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过，给出对应提示
        if (Objects.isNull(authenticate)) {
            throw new BadCredentialsException("校验错误");
        }
        // 如果认证通过了，使用UUID(用户ID)生成JWT
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // 把完整的用户信息存入redis
        String uuid = JwtUtil.getUUID();
        String jwt = JwtUtil.createJWT(uuid);
        loginUser.setUuid(uuid);
        RedisUser redisUser = new RedisUser();
        redisUser.setUsername(loginUser.getUsername());
        redisUser.setPassword(loginUser.getPassword());
        redisUser.setUuid(loginUser.getUuid());
        redisTemplate.opsForValue().set("login_" + uuid, redisUser, 2, TimeUnit.DAYS);
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> stringObjectMap = userService.CommonSelect(user.getAccount());
        result.put("code", 200);
        result.put("data", stringObjectMap.get("data"));
        result.put("message", "请求成功");
        result.put("token", jwt);
        return result;
    }

    @Override
    public Map<String, Object> logout() {
        // 获取SecurityContextHolder中的用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());
        RedisUser loginUser = (RedisUser) authentication.getPrincipal();
        String uuid = loginUser.getUuid();
        // 删除redis中的值
        redisTemplate.delete("login_" + uuid);
        return null;
    }
}

