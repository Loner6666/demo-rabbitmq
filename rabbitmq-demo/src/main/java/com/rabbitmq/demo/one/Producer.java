package com.rabbitmq.demo.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.demo.constants.Constant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description：生产者，发送消息
 * @Author：GuoFeng
 * @CreateTime：2022-05-02
 */
public class Producer {

    //发送消息
    public static void main(String[] args) throws IOException, TimeoutException {
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
        Channel channel = connection.createChannel();
        /**
         * 生产一个队列
         * 1. queue： 队列的名称 ；
         * 2. durable： 是否持久化 ；
         *      当durable = false时，队列非持久化。因为队列是存放在内存中的，所以当RabbitMQ重启或者服务器重启时该队列就会丢失 ；
         *      当durable = true时，队列持久化。当RabbitMQ重启后队列不会丢失。RabbitMQ退出时它会将队列信息保存到 Erlang自带的Mnesia数据库 中，当RabbitMQ重启之后会读取该数据库 ；
         * 3. exclusive： 是否排外的 ；
         *      当exclusive = true则设置队列为排他的。如果一个队列被声明为排他队列，该队列 仅对首次声明它的连接（Connection）可见，是该Connection私有的，类似于加锁，并在连接断开connection.close()时自动删除 ；
         *      当exclusive = false则设置队列为非排他的，此时不同连接（Connection）的管道Channel可以使用该队列 ；
         * 4. autoDelete： 是否自动删除 ；如果autoDelete = true，当所有消费者都与这个队列断开连接时，这个队列会自动删除。
         *      注意： 不是说该队列没有消费者连接时该队列就会自动删除，因为当生产者声明了该队列且没有消费者连接消费时，该队列是不会自动删除的。
         * 5. arguments： 设置队列的其他一些参数，如 x-rnessage-ttl 、x-expires 、x-rnax-length 、x-rnax-length-bytes、 x-dead-letter-exchange、 x-deadletter-routing-key 、 x-rnax-priority 等。
         */
        channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
        //发送的消息内容
        String message = "Hello world! Test RabbitMQ .";
        /**
         * 发送一个消费
         * 1、String exchange : 交换机名， 当不使用交换机时，传入""空串。
         * 2、String routingKey :（路由地址） 发布消息的队列，本次是队列的名称
         * 3、AMQP.BasicProperties props ：消息的配置属性
         * 4、byte[] body ：消息数据本体， 必须是byte数组
         */
        channel.basicPublish("", Constant.QUEUE_NAME, null, message.getBytes());
        System.out.println("消息发送完毕。");
    }

}
