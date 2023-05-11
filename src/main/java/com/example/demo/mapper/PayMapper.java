package com.example.demo.mapper;

import com.example.demo.entity.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PayMapper {

    @Select("select * from `pay` where body = #{account} and `mode`= #{mode}")
    List<Pay> SelectPayResult(String account,String mode);
}
