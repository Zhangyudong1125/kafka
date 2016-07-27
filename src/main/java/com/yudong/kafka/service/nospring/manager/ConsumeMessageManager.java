package com.yudong.kafka.service.nospring.manager;

import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.serialize.MessageSerializer;
import com.yudong.kafka.service.Factory.ConsumerFactory;
import com.yudong.kafka.service.consumer.process.BusinessProcessInterface;
import com.yudong.kafka.service.consumer.process.ProcessInvocationHandler;
import com.google.common.collect.Maps;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.consumer.TopicFilter;
import kafka.consumer.Whitelist;
import kafka.message.MessageAndMetadata;
import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * 消费消息
 * <p/>
 * <ul>
 * <li>方法列表</li>
 * </ul>
 *
 * @author zyd
 * @version 1.0.0 createTime: 15/6/3 上午9:17
 * @see
 * @since 1.6
 */
@Slf4j
public class ConsumeMessageManager {

    private static final int streamNum = 1;

    /**
     * 业务处理代理 缓存
     */
    public static Map<String,BusinessProcessInterface> businessProcessMap = Maps.newHashMap();

    /**
     * 消费消息
     *
     * @param businessProcessInstance       消费者实例
     */
    public static void process(final TopicInfo topicInfo, final BusinessProcessInterface businessProcessInstance, final String traceLogId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.debug("new thread.");
                ConsumeMessageManager.getMessagesAndProcess(topicInfo, businessProcessInstance, traceLogId);
            }
        }).start();
    }

    /**
     * 消费消息
     *
     * @param topicInfo topic信息
     */
    public static  <K,V> void getMessagesAndProcess(TopicInfo topicInfo,BusinessProcessInterface businessProcessInstance,String traceLogId) {
        TopicFilter topicFilter = new Whitelist(topicInfo.getTopicName()) ;
        Decoder decoder = new MessageSerializer<V>(new VerifiableProperties());
//        int brokerNum = Splitter.on(",").splitToList(topicInfo.getBrokerList()).size();//集群数
//        int connectNum = topicInfo.getPartition() / brokerNum ;//每台服务器上consumer线程数
        log.info("group name:{}", topicInfo.getGroupName());
        log.info("topic name:{}", topicInfo.getTopicName());
        List<KafkaStream<K, V>> consumerList = ConsumerFactory.getConnector(topicInfo, traceLogId).createMessageStreamsByFilter(topicFilter, streamNum, decoder, decoder);
        log.debug("traceLogId :{} class name:{}", traceLogId,businessProcessInstance.getClass().getName());
        for(KafkaStream<K,V> stream :consumerList){
            ConsumerIterator<K,V> consumerIterator = stream.iterator();
            while (consumerIterator.hasNext()){
                MessageAndMetadata<K,V> messageAndMetadata = consumerIterator.next();
                V message = messageAndMetadata.message();
                log.debug("message to consumer:traceLogId:{} topic:{} offset:{} key:{} partition:{} message:{}",
                        traceLogId, messageAndMetadata.topic(), messageAndMetadata.offset(), messageAndMetadata.key(), messageAndMetadata.partition(), message);
                businessProcessInstance.doBusiness(message);
                log.debug("business done.");
            }
        }
    }



    /**
     * 获取 业务实现类 代理缓存
     * @param topicInfo         topic信息
     * @param businessProcess       业务处理实现
     */
    public static BusinessProcessInterface getBusinessProcessProxy(TopicInfo topicInfo,Class<? extends BusinessProcessInterface> businessProcess)
            throws IllegalAccessException, InstantiationException {
        BusinessProcessInterface instance = businessProcess.newInstance();
        if (businessProcessMap.get(topicInfo.getTopicName()) == null){
            Object proxyInstance =  Proxy.newProxyInstance(
                    instance.getClass().getClassLoader(),
                    instance.getClass().getInterfaces(),
                    new ProcessInvocationHandler(instance));
            businessProcessMap.put(topicInfo.getTopicName(),(BusinessProcessInterface)proxyInstance);

            return (BusinessProcessInterface)proxyInstance;
        }
        return businessProcessMap.get(topicInfo.getTopicName());
    }

}
