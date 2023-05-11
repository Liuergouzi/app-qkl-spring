//package com.example.demo;
//
//import com.example.demo.entity.Mail;
//import com.example.demo.utils.Sgin;
//import org.junit.jupiter.api.Test;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//
//
////打包部署没必要添加test，造成额外消耗
//
//@SpringBootTest
//class DemoApplicationTests {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    MailReceiver mailReceiver;
//
//    @Test
//    void gdjj(){
//        rabbitTemplate.convertAndSend("myqueue","66996");
//    }
//
//    @Test
//    void sgin() {
//        Sgin sgin = new Sgin();
//        try {
//            System.out.println(sgin.md5("102121"));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //测试发送
//    @Test void send(){
//        Mail mail=new Mail("3217454073@qq.com","秦始皇","我是秦始皇，两千多年来我一直都没死，只是困在了地宫中。" +
//                "前些日子我逃了出来，但因为迷路走失了，现在我需要999.9元做高铁回西安，我等我回了西安把宝库取出来重新一统天下，到时候我封你为上将军！",
//                "C:\\Users\\Administrator\\Desktop\\轮子哥\\src\\main\\resources\\static\\qsh.webp");
//        try {
//            mailReceiver.sendMail(mail);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    //rabbit发送
//    @Test void rabbitSend(){
//        Mail mail=new Mail("3217454073@qq.com","秦始皇","我是秦始皇，两千多年来我一直都没死，只是困在了地宫中。" +
//                "前些日子我逃了出来，但因为迷路走失了，现在我需要999.9元做高铁回西安，我等我回了西安把宝库取出来重新一统天下，到时候我封你为上将军！",
//                "C:\\Users\\Administrator\\Desktop\\轮子哥\\src\\main\\resources\\static\\qsh.webp");
//        rabbitTemplate.convertAndSend("email-exchange","email-queue", mail);
//    }
//
//
//    @Test
//    void contextLoads() {
//    }
//
//}
