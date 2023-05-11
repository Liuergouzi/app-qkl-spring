package com.example.demo.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.service.*;
import com.example.demo.utils.JsonUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class DataApiController {

    private final LoginServcie loginServcie;
    private final UserService userService;
    private final NoticeService noticeService;
    private final ArticleService articleService;
    private final VersionService versionService;
    private final TaskPackService taskPackService;
    private final RecordService recordService;
    private final PayService payService;
    private final PrivateKeyService privateKeyService;
    private final RabbitTemplate rabbitTemplate;
    private final static String EXCHANGE_NAME = "app_msg_push_exchange";

    @Autowired
    public DataApiController(LoginServcie loginServcie, UserService userService, NoticeService noticeService, ArticleService articleService, VersionService versionService, TaskPackService taskPackService, RecordService recordService, PayService payService, PrivateKeyService privateKeyService, RabbitTemplate rabbitTemplate) {
        this.loginServcie = loginServcie;
        this.userService = userService;
        this.noticeService = noticeService;
        this.articleService = articleService;
        this.versionService = versionService;
        this.taskPackService = taskPackService;
        this.recordService = recordService;
        this.payService = payService;
        this.privateKeyService = privateKeyService;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 轮子哥
     * 账号登录接口
     */
    @CrossOrigin
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("account") String account, @RequestParam("password") String password) {
        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        return loginServcie.login(user);
    }

    /**
     * 轮子哥
     * 账号退出
     */
    @CrossOrigin
    @PostMapping("/logout")
    public Map<String, Object> logout() {
        return loginServcie.logout();
    }

    /**
     * 轮子哥
     * 账号注册接口
     */
    @CrossOrigin
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("account") String account,@RequestParam("password") String password,
                                        @RequestParam("invitation") String invitation) {
        return userService.RegisterInsert(account,password,invitation);
    }

    /**
     * 轮子哥
     * 查询用户所有信息接口
     */
    @CrossOrigin
    @PostMapping("/commonUser")
    public Map<String, Object> commonUser(@RequestParam("account") String account) {
        return userService.CommonSelect(account);
    }

    /**
     * 轮子哥
     * 手机号更换接口
     */
    @CrossOrigin
    @PostMapping("/phoneUpdate")
    public Map<String, Object> phoneUpdate(@RequestParam("account") String account,@RequestParam("phone") String phone) {
        return userService.phoneUpdate(account,phone);
    }

    /**
     * 轮子哥
     * 修改密码接口
     */
    @CrossOrigin
    @PostMapping("/passwordUpdate")
    public Map<String, Object> passwordUpdate(@RequestParam("account") String account,@RequestParam("password") String password) {
        return userService.PasswordUpdate(account,password);
    }

    /**
     * 轮子哥
     * 修改支付密码接口
     */
    @CrossOrigin
    @PostMapping("/payPasswordUpdate")
    public Map<String, Object> payPasswordUpdate(@RequestParam("account") String account,@RequestParam("payPassword") String payPassword) {
        return userService.PayPasswordUpdate(account,payPassword);
    }

    /**
     * 轮子哥
     * 个人信息页面查询接口
     */
    @CrossOrigin
    @PostMapping("/personalSelect")
    public Map<String, Object> personalSelect(@RequestParam("account") String account) {
        return userService.PersonalSelect(account);
    }

    /**
     * 轮子哥
     * 个人信息设置接口
     */
    @CrossOrigin
    @PostMapping("/personalUpdate")
    public Map<String, Object> personalUpdate(@RequestParam("account") String account,@RequestParam("mode") String mode,
                                              @RequestParam("value") String value) {
        return userService.PersonalUpdate(account,mode,value);
    }

    /**
     * 轮子哥
     * 公告接口
     */
    @CrossOrigin
    @PostMapping("/notice")
    public Map<String, Object> notice(@RequestParam("pageNum") int pageNum) {
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("total" ,noticeService.SelectNotice(pageNum).getPages());
        result.put("data",noticeService.SelectNotice(pageNum).getList());
        return result;
    }

    /**
     * 轮子哥
     *主页帖子接口
     */
    @CrossOrigin
    @PostMapping("/article")
    public Map<String, Object> article(@RequestParam("pageNum") int pageNum) {
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("total" ,articleService.SelectArticle(pageNum).getPages());
        result.put("data",articleService.SelectArticle(pageNum).getList());
        return result;
    }

    /**
     * 轮子哥
     *查看自己发布的帖子接口
     */
    @CrossOrigin
    @PostMapping("/myArticle")
    public Map<String, Object> myArticle(@RequestParam("account") String account,@RequestParam("pageNum") int pageNum) {
        PageInfo<Article> articlePageInfo = articleService.SelectMyArticle(account, pageNum);
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("total" ,articlePageInfo.getPages());
        result.put("data",articlePageInfo.getList());
        return result;
    }

    /**
     * 轮子哥
     * 帖子点赞接口
     */
    @CrossOrigin
    @PostMapping("/articleLoveCountUpdate")
    public void articleLoveCountUpdate(@RequestParam("id")Integer id,@RequestParam("name")String name,
                                       @RequestParam("account")String articleAccount) {
        articleService.ArticleLoveCountUpdate(id);

        rabbitTemplate.convertAndSend(EXCHANGE_NAME,articleAccount,"用户"+name+"点赞了你的作品");
    }

    /**
     * 轮子哥
     * 帖子发布接口
     */
    @CrossOrigin
    @PostMapping("/articleInsert")
    public Map<String, Object> articleInsert(@RequestParam("account") String account, @RequestParam("content") String content,
                                             @RequestParam("imageSize") Integer imageSize,@RequestParam("time") String time) {
        User user= (User) userService.CommonSelect(account).get("data");
        return articleService.ArticleInsert(account,content,time,imageSize,user.getName(),user.getHead());
    }

    /**
     * 轮子哥
     * 帖子删除接口
     */
    @CrossOrigin
    @PostMapping("/articleDelete")
    public Map<String, Object> articleDelete(@RequestParam("account") String account, @RequestParam("time") String time) {
        return articleService.ArticleDelete(account,time);
    }

    /**
     * 轮子哥
     *版本更新接口
     */
    @CrossOrigin
    @PostMapping("/version")
    public Map<String, Object> version() {
        return versionService.SelectVersionService();
    }

    /**
     * 轮子哥
     *支付结果查询接口
     */
    @CrossOrigin
    @PostMapping("/pay")
    public Map<String, Object> pay(String account,String mode) {
        return payService.SelectPayResultService(account,mode);
    }

    /**
     * 轮子哥
     *获取key接口
     */
    @CrossOrigin
    @PostMapping("/key")
    public Map<String, Object> key(String name) {
        return privateKeyService.PrivateKeyService(name);
    }

    /**
     * 轮子哥
     * 查看好友邀请明细
     */
    @CrossOrigin
    @PostMapping("/invitationSelect")
    public Map<String, Object> invitationSelect(@RequestParam("account") String account,@RequestParam("pageNum") int pageNum) {
        User user= (User) userService.CommonSelect(account).get("data");
        System.out.println(user.getMa());
        return userService.InvitationSelect(user.getMa(),pageNum);
    }

    /**
     *轮子哥
     * 任务包兑换
     */
    @CrossOrigin
    @PostMapping("/buyTask")
    public Map<String, Object> buyTask(@RequestParam("account") String account,@RequestParam("type") Integer type) {
        Integer surplus = null;
        String quota="";
        switch (type.toString()){
            case "1":surplus=10;quota="0.4";break;
            case "2":surplus=30;quota="1.22";break;
            case "3":surplus=100;quota="4.13";break;
            case "4":surplus=300;quota="12.6";break;
            case "5":surplus=1000;quota="42.66";break;
        }
        Map<String, Object> result=userService.BalanceUpdate(account,String.valueOf(surplus),"reduce");
        if (result.get("code").equals(200)){
            recordService.RecordInsert(account,"-"+surplus,"任务消耗","任务包");
            return taskPackService.InsertTask(account,type,surplus,quota);
        }else {
            return result;
        }
    }

    /**
     * 轮子哥
     * 任务总计算
     */
    @CrossOrigin
    @PostMapping("/taskPackTotal")
    public Map<String, Object> taskPackTotal(@RequestParam("account") String account) {
        return taskPackService.TaskPackTotal(account);
    }

    /**
     * 轮子哥
     * 领取每日任务
     */
    @CrossOrigin
    @PostMapping("/taskPackReceive")
    public Map<String, Object> taskPackReceive(@RequestParam("account") String account) {
        JSONObject taskPackTotal = new JSONObject((Map) taskPackService.TaskPackTotal(account).get("data"));
        String quota= String.valueOf(taskPackTotal.get("dayTotal"));
        Map<String, Object> updateState =taskPackService.TaskPackReceive(account);
        if (updateState.get("code").equals(200)){
            User user= (User) userService.CommonSelect(account).get("data");
            String masterQuota = String.valueOf(Float.valueOf(quota)/20);
            userService.BalanceUpdate(account,quota,"increase");
            recordService.RecordInsert(account,quota,"每日任务","任务包");
            if (user.getInvitation()!=null){
                User master= (User) userService.MasterSelect(user.getInvitation()).get("data");
                userService.BalanceUpdate(master.getAccount(),masterQuota,"increase");
                recordService.RecordInsert(master.getAccount(),masterQuota,"邀请奖励","用户"+user.getMa());
            }
            return new JsonUtil().successReturns("领取成功");
        }else {
            return updateState;
        }
    }

    /**
     * 轮子哥
     * 积分查询接口
     */
    @CrossOrigin
    @PostMapping("/recordSelect")
    public Map<String, Object> recordSelect(@RequestParam("account") String account, @RequestParam("pageNum") int pageNum) {
        User user= (User) userService.CommonSelect(account).get("data");
        Map<String, Object> result = recordService.SelectRecord(account,pageNum);
        result.put("balance",user.getBalance());
        return result;
    }




}
