package com.example.demo.controller;

import com.example.demo.utils.JsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class RabbitPushApiController {

    /**
     * 轮子哥
     * 向指定用户(app.xxx.#)&&所有用户(app.msg.#)转发推送
     */

    private final static String EXCHANGE_NAME = "app_msg_push_exchange";
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitPushApiController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/rabbitPush")
    public Map<String, Object> logout(@RequestParam("to") String to,@RequestParam("message") String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,to,message);
//        if(!rabbitTemplate.waitForConfirms(60000)){
//            Timer timer = new Timer();
//            TimerTask timerTask = new TimerTask()
//            {
//                @Override
//                public void run()
//                { rabbitTemplate.convertAndSend(EXCHANGE_NAME,to,message); }
//            };
//            timer.schedule(timerTask, 0, 20000);
//        }
        return new JsonUtil().successReturns("推送成功");
    }
}
