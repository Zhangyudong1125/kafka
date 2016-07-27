package com.yudong.kafka.model;

/**
 * Created by zyd on 14/10/29.
 * kafka 配置信息
 */
public interface KafkaProperties {

/*********************************** consumer config start ***********************************************/
    /**
     * zk 连接超时时间
     */
    String zookeeperConnectionTimeout = "60000";
    String socketTimeout = "30000";
//        socket.receive.buffer.bytes	        64 * 1024
//        fetch.message.max.bytes	            1024 * 1024
//        auto.commit.enable	                TRUE
//        auto.commit.interval.ms	            60 * 1000
//        queued.max.message.chunks	        10
//        rebalance.max.retries	            4
//        fetch.min.bytes	                    1
//        fetch.wait.max.ms	                100
//        rebalance.backoff.ms	            2000
//        refresh.leader.backoff.ms	        200
//        auto.offset.reset	                largest
//        consumer.timeout.ms	                -1
//        zookeeper.session.timeout.ms 	    6000
//        zookeeper.connection.timeout.ms	    6000
//        zookeeper.sync.time.ms 	            2000

/*********************************** consumer config end  ***********************************************/

/*********************************** producer config start ***********************************************/
    /**
     * 消息序列化类
     */
    String serializerClass = "MessageSerializer";
    /**
     * 消息发送失败重试次数
     */
    String produceRetries = "1";

/*********************************** producer config end  ***********************************************/
}
