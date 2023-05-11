package com.example.demo.mapper;

import com.example.demo.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeMapper {

    @Select("select * from `notice`")
    List<Notice> SelectNoticeMapper();
}
