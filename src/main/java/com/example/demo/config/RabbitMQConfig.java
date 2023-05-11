package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final static String EXCHANGE_NAME = "app_msg_push_exchange";

//    /**
//     *Fanout类型,不使用邮箱
//     */
//    //声明交换机
//    @Bean
//    public FanoutExchange emailExchange() {
//        return new FanoutExchange("myExchange", true, false);
//    }
//    //声明队列
//    @Bean
//    public Queue emailQueue() {
//        return new Queue("myqueue", true);
//    }
//    //绑定
//    @Bean
//    public Binding bindingEmailDirect() {
//        return BindingBuilder.bind(emailQueue()).to(emailExchange());
//    }


    //topic声明交换器
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }
    //topic声明队列
    @Bean
    public Queue appQueue() {
        return new Queue("app_queue", true);
    }
    //topic绑定交换机与队列
    @Bean
    public Binding getMyqueue() {
        return BindingBuilder.bind(appQueue()).to(topicExchange()).with("app.msg.*");
    }


    //转成json格式
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    //确认机制错误返回
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //确认消息送到交换机(Exchange)回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("\n确认消息送到交换机(Exchange)结果：");
            System.out.println("相关数据：" + correlationData);
            System.out.println("是否成功：" + ack);
            System.out.println("错误原因：" + cause);
        });

        //确认消息送到队列(Queue)回调
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            System.out.println("\n确认消息送到队列(Queue)结果：");
            System.out.println("发生消息：" + returnedMessage.getMessage());
            System.out.println("回应码：" + returnedMessage.getReplyCode());
            System.out.println("回应信息：" + returnedMessage.getReplyText());
            System.out.println("交换机：" + returnedMessage.getExchange());
            System.out.println("路由键：" + returnedMessage.getRoutingKey());
        });
        return rabbitTemplate;
    }


}
