package com.example.demo.mapper;

import com.example.demo.entity.Version;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VersionMapper {

    @Select("select * from `version`")
    Version SelectVersionMapper();
}
