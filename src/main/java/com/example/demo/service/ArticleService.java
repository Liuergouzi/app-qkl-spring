package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.utils.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleService {

    private final ArticleMapper articleMapper;
    private final JsonUtil jsonUtil;
    private final RedisTemplate<String,Article> redisTemplate;

    @Autowired
    public ArticleService(ArticleMapper articleMapper, JsonUtil jsonUtil, RedisTemplate<String, Article> redisTemplate) {
        this.articleMapper = articleMapper;
        this.jsonUtil = jsonUtil;
        this.redisTemplate = redisTemplate;
    }


    /**
     *轮子哥
     * 查看所有
     */
    public PageInfo<Article> SelectArticle(int pageNum) {
        PageHelper.startPage(pageNum, 16);
        PageHelper.orderBy("id desc");
        List<Article> articles = articleMapper.SelectArticleMapper();
        return new PageInfo<>(articles);
    }

    /**
     *轮子哥
     * 查看自己
     */
    public PageInfo<Article> SelectMyArticle(String account,int pageNum) {
        PageHelper.startPage(pageNum, 16);
        PageHelper.orderBy("id desc");
        List<Article> range = redisTemplate.opsForList().range("myArticle:"+account, 0, -1);
        if (range != null && !range.isEmpty()){
            return new PageInfo<>(range);
        }else {
            List<Article> articles = articleMapper.SelectMyArticleMapper(account);
            for (Article article:articles) {
                redisTemplate.opsForList().rightPush("myArticle:"+account,article);
            }
            return new PageInfo<>(articles);
        }
    }


    /**
     *轮子哥
     * 点赞
     */
    public void ArticleLoveCountUpdate(Integer id) {
        articleMapper.ArticleLoveCountUpdateMapper(id);
    }


    /**
     *轮子哥
     * 发布
     */
    public Map<String, Object> ArticleInsert(String account,String content,String time,Integer imageSize,String name,String head) {
        Integer insert_count=articleMapper.ArticleInsertMapper(account,content,time,imageSize,name,head);
        if (insert_count == 1) {
            redisTemplate.delete("myArticle:"+account);
            return jsonUtil.successReturns("发布成功");
        } else {
            return jsonUtil.falseReturns("发布失败");
        }
    }

    /**
     *轮子哥
     * 帖子删除发布
     */
    public Map<String, Object> ArticleDelete(String account,String time) {
        Integer delete_count=articleMapper.ArticleDeleteMapper(account,time);
        if (delete_count == 1) {
            redisTemplate.delete("myArticle:"+account);
            return jsonUtil.successReturns("删除成功");
        } else {
            return jsonUtil.falseReturns("删除失败");
        }
    }


}
