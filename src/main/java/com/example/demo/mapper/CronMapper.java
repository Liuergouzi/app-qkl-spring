package com.example.demo.mapper;

import com.example.demo.entity.Cron;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CronMapper {
    @Select("select * from cron limit 1")
    Cron getCron();
}
