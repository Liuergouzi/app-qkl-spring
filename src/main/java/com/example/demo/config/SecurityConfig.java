package com.example.demo.config;

import com.example.demo.exception.AccessDenied;
import com.example.demo.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 轮子哥
 * security配置
 */
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDenied accessDenied;

    @Autowired
    public SecurityConfig(AccessDenied accessDenied, AuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter) {
        this.accessDenied = accessDenied;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    //加密校验，创建BCryptPasswordEncoder注入容器
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //接口放行，把身份验证管理器注入容器
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //定义拦截规则
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()// CSRF禁用，因为不使用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 不通过Session获取SecurityContext
                .and()// 再次获取到HttpSecurity对象
                .authorizeRequests()// 进行认证请求的配置
                .antMatchers("/api/login", "/api/register", "/api/passwordUpdate", "/api/rabbitPush").anonymous()//开放接口
                .anyRequest().authenticated();// 除上面外的所有请求全部需要鉴权认证

        // 配置异常处理器
        httpSecurity.exceptionHandling()
                // 配置认证失败处理器
                .accessDeniedHandler(accessDenied)
                .authenticationEntryPoint(authenticationEntryPoint);

        // 允许跨域
        httpSecurity.cors();

        // 把token校验过滤器添加到过滤器链中
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}


