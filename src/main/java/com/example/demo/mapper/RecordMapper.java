package com.example.demo.mapper;

import com.example.demo.entity.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordMapper {

    @Insert("insert into `record`  (account,count,time,message,source)values(#{account},#{count},#{time},#{message},#{source})")
    Integer RecordInsertMapper(String account,String count,String time,String message,String source);

    @Select("select * from `record` where `account` =#{account}")
    List<Record> SelectRecord(String account);

}
