package com.example.demo.service;

import com.example.demo.entity.Invitation;
import com.example.demo.entity.LoginUser;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.utils.JsonUtil;
import com.example.demo.utils.MD5;
import com.example.demo.utils.NowTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final JsonUtil jsonUtil;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, User> redisTemplate;

    @Autowired
    public UserService(UserMapper userMapper, JsonUtil jsonUtil, PasswordEncoder passwordEncoder, RedisTemplate<String, User> redisTemplate) {
        this.userMapper = userMapper;
        this.jsonUtil = jsonUtil;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
    }


    /**
     * 轮子哥
     * 根据账号查询数据
     */
    public Map<String, Object> CommonSelect(String account) {

        User userRedis = redisTemplate.opsForValue().get("user:" + account);
        if (userRedis != null) {
            return jsonUtil.successReturns(userRedis);
        } else {
            User users = userMapper.CommonSelectMapper(account);
            redisTemplate.opsForValue().set("user:" + account, users);
            return jsonUtil.successReturns(users);
        }
    }

    /**
     * 轮子哥
     * 重写登录
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userMapper.LoginMapper(username);
        // 查询用户信息（查询数据库，这里使用假数据）
        if (user.size() == 0) {
            throw new RuntimeException("用户名或密码错误");
        }
        String password = user.get(0).getPassword();
        // 把数据封装成LoginUser对象返回
        return new LoginUser(username, password);
    }

    /**
     * 轮子哥
     * 查询上级
     */
    public Map<String, Object> MasterSelect(String ma) {
        User users = userMapper.maSelectMapper(ma);
        return jsonUtil.successReturns(users);
    }

    /**
     * 轮子哥
     * 更据邀请码查询部分数据
     */
    public Map<String, Object> InvitationSelect(String ma, int pageNum) {
        PageHelper.startPage(pageNum, 20);
        PageHelper.orderBy("id desc");
        List<Invitation> invitation = userMapper.InvitationSelectMapper(ma);
        int index = 0;
        for (Invitation i : invitation) {
            if (i.getDateTime().substring(0, 10).equals(new NowTime().time().substring(0, 10))) {
                index++;
            }
        }
        PageInfo<Invitation> invitationPageInfo = new PageInfo<>(invitation);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("dayTotal", index);
        result.put("invitationTotal", invitationPageInfo.getTotal());
        result.put("total", invitationPageInfo.getPages());
        result.put("data", invitationPageInfo.getList());
        return result;
    }

    /**
     * 轮子哥
     * 注册
     */
    public Map<String, Object> RegisterInsert(String account, String password, String invitation) {

        if (userMapper.InvitationSelectMapper(invitation) != null) {

            if (CommonSelect(account).get("data") == null) {
                int iRandom = (int) (1 + Math.random() * 10);
                String ma = Objects.requireNonNull(MD5.md5(account)).substring(0, 7) + iRandom;
                Integer insert_count = userMapper.RegisterInsertMapper(account, passwordEncoder.encode(password), invitation, ma, new NowTime().time());
                if (insert_count == 1) {
                    return jsonUtil.successReturns("注册成功");
                } else {
                    return jsonUtil.falseReturns("注册失败");
                }
            } else {
                return jsonUtil.falseReturns("该账号已被注册");
            }
        } else {
            return jsonUtil.falseReturns("邀请码不存在");
        }

    }

    /**
     * 轮子哥
     * 登录查询
     */
    public Map<String, Object> PasswordSelect(String account, String password) {
        List<User> users = userMapper.PasswordSelectMapper(account, passwordEncoder.encode(password));
        if (users.size() == 1) {
            return jsonUtil.successReturns(users.get(0));
        } else if (users.size() == 0) {
            return jsonUtil.falseReturns("账号或密码错误");
        } else {
            return jsonUtil.falseReturns("账号异常");
        }
    }


    /**
     * 轮子哥
     * 更改密码
     */
    public Map<String, Object> PasswordUpdate(String account, String password) {
        Integer update_count = userMapper.PasswordUpdateMapper(account, passwordEncoder.encode(password));
        if (update_count == 1) {
            redisTemplate.delete("user:" + account);
            return jsonUtil.successReturns("密码修改成功");
        } else {
            return jsonUtil.falseReturns("密码修改失败");
        }
    }

    /**
     * 轮子哥
     * 更改支付密码
     */
    public Map<String, Object> PayPasswordUpdate(String account, String payPassword) {
        Integer update_count = userMapper.PayPasswordUpdateMapper(account, payPassword);
        if (update_count == 1) {
            redisTemplate.delete("user:" + account);
            return jsonUtil.successReturns("支付密码修改成功");
        } else {
            return jsonUtil.falseReturns("支付密码修改失败");
        }
    }

    /**
     * 轮子哥
     * 手机号数量查询
     */
    public Long phoneSelect(String phone) {
        return userMapper.PhoneSelectMapper(phone);
    }


    /**
     * 轮子哥
     * 更换手机号
     */
    public Map<String, Object> phoneUpdate(String account, String phone) {
        Long phone_size = phoneSelect(phone);
        if (phone_size == 0) {
            Integer update_count = userMapper.PhoneUpdateMapper(account, phone);
            if (update_count == 1) {
                redisTemplate.delete("user:" + account);
                return jsonUtil.successReturns("手机号更换绑定成功");
            } else {
                return jsonUtil.falseReturns("手机号更换绑定失败");
            }
        } else {
            return jsonUtil.falseReturns("绑定失败，该手机号已被绑定");
        }
    }


    /**
     * 轮子哥
     * 余额更新
     */
    public Map<String, Object> BalanceUpdate(String account, String balance, String mode) {
        Map<String, Object> userMap = CommonSelect(account);
        User user = (User) userMap.get("data");
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        BigDecimal old_balance = new BigDecimal(user.getBalance());
        BigDecimal new_balance = new BigDecimal(decimalFormat.format(Double.parseDouble(balance)));
        switch (mode) {
            case "reduce":
                if (old_balance.subtract(new_balance).doubleValue() >= 0) {
                    Integer update_count = userMapper.BalanceUpdateMapper(account, old_balance.subtract(new_balance).toString());
                    if (update_count == 1) {
                        redisTemplate.delete("user:" + account);
                        return jsonUtil.successReturns("余额更新成功");
                    } else {
                        return jsonUtil.falseReturns("当前余额未改变");
                    }
                } else {
                    return jsonUtil.falseReturns("余额不足");
                }
            case "increase":
                Integer update_count = userMapper.BalanceUpdateMapper(account, old_balance.add(new_balance).toString());
                if (update_count == 1) {
                    redisTemplate.delete("user:" + account);
                    return jsonUtil.successReturns("余额更新成功");
                } else {
                    return jsonUtil.falseReturns("当前余额未改变");
                }
            default:
                return jsonUtil.falseReturns("非法请求");
        }
    }

    /**
     * 轮子哥
     * 个人头像、名称、地址修改
     */
    public Map<String, Object> PersonalUpdate(String account, String mode, String value) {
        Integer update_count = null;
        switch (mode) {
            case "name":
                update_count = userMapper.NameUpdateMapper(account, value);
                break;
            case "head":
                update_count = userMapper.HeadUpdateMapper(account, value);
                break;
            case "address":
                update_count = userMapper.AddressUpdateMapper(account, value);
                break;
            default:
                return jsonUtil.falseReturns("非法请求");
        }
        if (update_count == 1) {
            redisTemplate.delete("user:" + account);
            return jsonUtil.successReturns("修改成功");
        } else {
            return jsonUtil.falseReturns("未修改");
        }
    }

    /**
     * 轮子哥
     * 个人信息查询
     */
    public Map<String, Object> PersonalSelect(String account) {
        User user = userMapper.personalSelectMapper(account);
        Map<String, Object> result = new HashMap<>();
        result.put("name", user.getName());
        result.put("head", user.getHead());
        result.put("address", user.getAddress());
        result.put("invitation", user.getInvitation());
        result.put("ma", user.getMa());
        result.put("master", "noExist");
        if (user.getInvitation() != null && !user.getInvitation().equals("")) {
            User master = userMapper.maSelectMapper(user.getInvitation());
            result.put("master", "dataEmpty");
            if (master != null) {
                result.put("master", "exist");
                result.put("masterName", master.getName());
                result.put("masterHead", master.getHead());
            }
        }
        return jsonUtil.successReturns(result);
    }


}
