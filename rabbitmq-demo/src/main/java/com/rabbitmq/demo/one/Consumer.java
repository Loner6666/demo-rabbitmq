package com.rabbitmq.demo.one;

import com.rabbitmq.client.*;
import com.rabbitmq.demo.constants.Constant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description：消费者，接收消息
 * @Author：GuoFeng
 * @CreateTime：2022-05-02
 */
public class Consumer {

    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constant.HOST);
        factory.setUsername(Constant.USER_NAME);
        factory.setPassword(Constant.PASS_WORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明 接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println(new String(message.getBody()));
        };
        //取消消息时的回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };

        /**
         * 消费者消费消息
         * 1、String queue：队列名称
         * 2、boolean autoAck：消费成功后是否自动确认消息,true自动确认,false不自动要手动调用,建立设置为false
         * 3、消费者未成功消费的回调
         * 4、消费者取消消费的回调
         */
        channel.basicConsume(Constant.QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
