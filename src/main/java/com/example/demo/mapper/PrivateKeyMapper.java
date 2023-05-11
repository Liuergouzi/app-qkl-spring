package com.example.demo.mapper;

import com.example.demo.entity.PrivateKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PrivateKeyMapper {

    @Select("select keyValue from `privatekey` where name = #{name}")
    PrivateKey SelectKeyMapper(String name);

}
