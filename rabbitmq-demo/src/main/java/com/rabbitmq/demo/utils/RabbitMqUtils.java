package com.rabbitmq.demo.utils;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.demo.constants.Constant;

/**
 * @Description：RabbitMq工具类，连接工厂创建信道
 * @Author：GuoFeng
 * @CreateTime：2022-05-24
 */
public class RabbitMqUtils {

    /**
     * 获取一个RabbitMq的信道
     *
     * @return 信道
     * @throws Exception 异常
     */
    public static Channel getChannel() throws Exception {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂IP，连接RabbitMQ的队列
        factory.setHost(Constant.HOST);
        //用户名
        factory.setUsername(Constant.USER_NAME);
        //密码
        factory.setPassword(Constant.PASS_WORD);
        //创建连接
        Connection connection = factory.newConnection();
        //获取信道
        com.rabbitmq.client.Channel channel = connection.createChannel();
        return channel;
    }

}
