package com.yudong.kafka.service.Factory;

import com.yudong.kafka.model.KafkaProperties;
import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.tools.IntegerCycle;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zyd on 14-10-17.
 * 产生不同的producer
 */
@Slf4j
public class ProducerFactory {


    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 每个 topic 的producer个数
     */
    private final static int producerNum = 1 ;
    /**
     * 缓存topic producer
     */
    public static Map<String,List<Producer>> topicMap = Maps.newHashMap();

    public static void initProducer(TopicInfo topicInfo){
        List<Producer> producerList = Lists.newArrayList();
        for (int i = 0;i < producerNum ; i++ ){
            producerList.add(new Producer(new ProducerConfig(getProperty(topicInfo))));
            log.debug("initProducer :{},{}",topicInfo.getTopicName(),i);
        }
        topicMap.put(topicInfo.getTopicName(),producerList);
    }

    /**
     * 设置 producer 参数
     * @return          Properties
     */
    private static Properties getProperty(TopicInfo topicInfo) {
        Properties properties = new Properties();
        properties.put("metadata.broker.list", topicInfo.getBrokerList());
        properties.put("request.required.acks", topicInfo.getAck().getAck());
        properties.put("producer.type", topicInfo.getSyncFlag().getSyncFlag());
        properties.put("serializer.class", KafkaProperties.serializerClass);
        properties.put("message.send.max.retries", topicInfo.isRetries()? KafkaProperties.produceRetries:0);
        properties.put("request.timeout.ms", "300");

//        request.timeout.ms	                10000
//        key.serializer.class
//        partitioner.class	                kafka.producer.DefaultPartitioner
//        compression.codec	                none
//        compressed.topics	                null
//        retry.backoff.ms	                100
//        topic.metadata.refresh.interval.ms	600 * 1000
//        queue.buffering.max.ms	            5000
//        queue.buffering.max.messages	    10000
//        queue.enqueue.timeout.ms	        -1
//        batch.num.messages	                200
//        send.buffer.bytes	                100 * 1024
//        client.id	                        ""
        return properties;
    }

    /**
     * 创建 Producer
     * @param topicInfo topic 信息
     * @return Producer
     */
    public static Producer getProducer(TopicInfo topicInfo) {
        if (topicMap.get(topicInfo.getTopicName()) == null || topicMap.get(topicInfo.getTopicName()).size() == 0){
            initProducer(topicInfo);
        }
        int n = IntegerCycle.getIndex(atomicInteger, producerNum);
        log.debug("index of connector ：{}",n);
        return topicMap.get(topicInfo.getTopicName()).get(n);
    }

}
