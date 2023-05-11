package com.example.demo.filter;

import com.example.demo.entity.RedisUser;
import com.example.demo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final RedisTemplate<String, RedisUser> redisTemplate;

    @Autowired
    public JwtAuthenticationTokenFilter(RedisTemplate<String, RedisUser> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        // token是空则放行
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String uuid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            uuid = claims.getSubject();
        } catch (Exception e) {
            request.setAttribute("errorMessage", "10001");
            throw new BadCredentialsException("");
        }
        // 从redis中获取用户信息
        String redisKey = "login_" + uuid;
        RedisUser RedisUser = redisTemplate.opsForValue().get(redisKey);

        if (Objects.isNull(RedisUser)) {
            request.setAttribute("errorMessage", "10002");
            throw new BadCredentialsException("");
        }

        System.out.println(RedisUser.toString());
        // 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(RedisUser, null, null);
        // 存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }
}


