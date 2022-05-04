Rabbitmq的六种模式分别为：simple简单模式、work工作模式、publish/subscribe订阅模式、routing路由模式、topic 主题模式、RPC模式。

1、simple简单模式为一个队列中一条消息，只能被一个消费者消费。
2、Work工作模式为一个生产者，多个消费者，每个消费者获取到的消息唯一。
3、publish/subscribe订阅模式为一个生产者发送的消息被多个消费者获取。
4、routing路由模式为生产者发送的消息主要根据定义的路由规则决定往哪个队列发送。
5、topic 主题模式为生产者，一个交换机(topicExchange)，模糊匹配路由规则，多个队列，多个消费者。
6、RPC模式为客户端 Client 先发送消息到消息队列，远程服务端 Server 获取消息，然后再写入另一个消息队列，向原始客户端 Client 响应消息处理结果。


