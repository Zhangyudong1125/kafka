package com.yudong.kafka.service.Factory;

import com.yudong.kafka.model.KafkaProperties;
import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.tools.IPUtil;
import com.google.common.collect.Maps;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by zyd on 14-10-19.
 * 生成不同的 consumer
 */
@Slf4j
public class ConsumerFactory {

    /**
     * 缓存topic consumer
     */
    public static Map<String,ConsumerConnector> consumerMap = Maps.newHashMap();

    /**
     * 初始化 producer
     *
     * @param topicInfo     topic信息
     */
    public static void initConsumer(TopicInfo topicInfo){
//        int brokerNum = Splitter.on(",").splitToList(topicInfo.getBrokerList()).size();//集群数
//        int connectNum = topicInfo.getPartition() / brokerNum ;//每台服务器上的消费者连接数
        ConsumerConnector connector = Consumer.createJavaConsumerConnector((new ConsumerConfig(getProperty(topicInfo))));
        consumerMap.put(topicInfo.getTopicName(),connector);
    }

    /**
     * 获取缓存的 connector
     *
     * @param topicInfo     topic信息
     * @return connector
     */
    public static ConsumerConnector getConnector(TopicInfo topicInfo,String traceLogId) {
        if(consumerMap.get(topicInfo.getTopicName()) == null){
            initConsumer(topicInfo);
        }
        log.debug("traceLogId:{} ",traceLogId);
        return consumerMap.get(topicInfo.getTopicName());
    }

    private static Properties getProperty(TopicInfo topicInfo) {
        Properties props = new Properties();
        props.put("zookeeper.connect", topicInfo.getZkAddress());
        props.put("group.id", topicInfo.getGroupName());
        String flag = IPUtil.getLocalIP()+"_"+ topicInfo.getTopicName()+"_"+ UUID.randomUUID().toString();
        props.put("client.id", flag);
        props.put("consumer.id", flag);
        props.put("zookeeper.connection.timeout.ms", KafkaProperties.zookeeperConnectionTimeout);
        props.put("auto.commit.interval.ms", "3000");

//        socket.timeout.ms	                30 * 1000
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
        log.debug("consumer Properties:{}",props);
        return props;
    }
}
