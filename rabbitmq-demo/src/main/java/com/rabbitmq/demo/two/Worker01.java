package com.rabbitmq.demo.two;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.demo.constants.Constant;
import com.rabbitmq.demo.utils.RabbitMqUtils;

/**
 * @Description：这是一个工作线程（相当于之前的消费者）
 * @Author：GuoFeng
 * @CreateTime：2022-05-24
 */
public class Worker01 {

    /**
     * 接收消息
     *
     * @param args 参数
     */
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        //消息的接收
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到的消息：" + new String(message.getBody()));
        };

        //消息接收被取消时，执行下面的内容
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };

        //消息的接收
        /**
         * 消费者消费消息
         * 1、String queue：队列名称
         * 2、boolean autoAck：消费成功后是否自动确认消息,true自动确认,false不自动要手动调用,建立设置为false
         * 3、消费者未成功消费的回调
         * 4、消费者取消消费的回调
         */
        System.out.println("C1等待接收消息。。。。。。");
        channel.basicConsume(Constant.QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
