package com.example.demo.mapper;

import com.example.demo.entity.Invitation;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {

    @Select("select * from `user`  where  `account`= #{account}")
    User CommonSelectMapper(String account);

    @Select("select * from `user`  where  `account`= #{account}")
    List<User> LoginMapper(String account);

    @Select("select name,head,ma,dateTime from `user`  where  `invitation`= #{invitation}")
    List<Invitation> InvitationSelectMapper(String invitation);

    @Select("select account,name,head from `user`  where  `ma`= #{ma}")
    User maSelectMapper(String ma);

    @Select("select name,head,address,ma,invitation from `user` where `account`= #{account}")
    User personalSelectMapper(String account);

    @Select("select * from `user`  where  `account`= #{account} and `password` = #{password}")
    List<User> PasswordSelectMapper(String account,String password);

    @Select("select count(*) from `user`  where  `phone`= #{phone}")
    Long PhoneSelectMapper(String phone);

    @Update("update `user` set `password`=#{password}  where `account`=#{account}")
    Integer PasswordUpdateMapper(String account,String password);

    @Update("update `user` set `phone`=#{phone}  where `account`=#{account}")
    Integer PhoneUpdateMapper(String account,String phone);

    @Update("update `user` set `name`=#{name}  where `account`=#{account}")
    Integer NameUpdateMapper(String account,String name);

    @Update("update `user` set `head`=#{head}  where `account`=#{account}")
    Integer HeadUpdateMapper(String account,String head);

    @Update("update `user` set `address`=#{address}  where `account`=#{account}")
    Integer AddressUpdateMapper(String account,String address);

    @Update("update `user` set `payPassword`=#{payPassword}  where `account`=#{account}")
    Integer PayPasswordUpdateMapper(String account,String payPassword);

    @Update("update `user` set `balance`=#{balance}  where `account`=#{account}")
    Integer BalanceUpdateMapper(String account,String balance);

    @Insert("insert into user (account,password,invitation,ma,dateTime)values (#{account},#{password},#{invitation},#{ma},#{dateTime})")
    Integer RegisterInsertMapper(String account,String password,String invitation,String ma,String dateTime);

}
