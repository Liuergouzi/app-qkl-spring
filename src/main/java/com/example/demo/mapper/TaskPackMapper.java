package com.example.demo.mapper;


import com.example.demo.entity.TaskPack;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TaskPackMapper {

    @Select("select * from `taskpack` where account=#{account} and `frequency` > 0 ")
    List<TaskPack> TaskPackTotalMapper(String account);

    @Update("update `taskpack` set `time`=#{time} where account=#{account} and `frequency` > 0 ")
    Integer UpdateTimeMapper(String account,String time);

    @Update("update `taskpack` set `frequency`=frequency-1 where account=#{account} and `frequency` > 0 ")
    Integer UpdateFrequencyMapper(String account);

    @Insert("insert into `taskpack`  (account,type,surplus,quota,frequency)values(#{account},#{type},#{surplus},#{quota},#{frequency})")
    Integer TaskPackInsertMapper(String account,Integer type,Integer surplus,String quota,Integer frequency);

}
