package com.example.demo.config;

import com.example.demo.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 轮子哥
 * RedisConfig配置类
 */
@Configuration
public class RedisConfig {

    /**
     * 定制RedisUser类专属序列化
     */

    @Bean
    public RedisTemplate<String, RedisUser> redisTemplateRedisUser(RedisConnectionFactory factory) {
        RedisTemplate<String, RedisUser> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisUser.class));
        return template;
    }

    /**
     * 定制User类专属序列化
     */

    @Bean
    public RedisTemplate<String, User> redisTemplateUser(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        return template;
    }


    /**
     * 定制Article类专属序列化
     */

    @Bean
    public RedisTemplate<String, Article> redisTemplateArticle(RedisConnectionFactory factory) {
        RedisTemplate<String, Article> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Article.class));
        return template;
    }

    /**
     * 定制Notice类专属序列化
     */

    @Bean
    public RedisTemplate<String, Notice> redisTemplateNotice(RedisConnectionFactory factory) {
        RedisTemplate<String, Notice> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Notice.class));
        return template;
    }

    /**
     * 定制PrivateKey类专属序列化
     */

    @Bean
    public RedisTemplate<String, PrivateKey> redisTemplatePrivateKey(RedisConnectionFactory factory) {
        RedisTemplate<String, PrivateKey> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(PrivateKey.class));
        return template;
    }

}

