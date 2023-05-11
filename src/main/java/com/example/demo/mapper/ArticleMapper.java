package com.example.demo.mapper;

import com.example.demo.entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {
    @Select("select * from `article`")
    List<Article> SelectArticleMapper();

    @Select("select * from `article` where `account`= #{account}")
    List<Article> SelectMyArticleMapper(String account);

    @Update("update `article` set `loveCount`=loveCount+1  where `id`=#{id}")
    Integer ArticleLoveCountUpdateMapper(Integer id);

    @Insert("insert into `article` (account,content,time,imageSize,name,head) values (#{account},#{content},#{time},#{imageSize},#{name},#{head})")
    Integer ArticleInsertMapper(String account,String content,String time,Integer imageSize,String name,String head);

    @Delete("delete from `article` where `account`= #{account} and `time`=#{time}")
    Integer ArticleDeleteMapper(String account,String time);

}
