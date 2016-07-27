package com.yudong.kafka.service.nospring;

import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.service.consumer.process.BusinessProcessInterface;
import com.yudong.kafka.service.nospring.manager.ConsumeMessageManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 类注释
 * <p/>
 * <ul>
 * <li>kafka消息消费，不依赖spring</li>
 * </ul>
 *
 * @author zyd
 * @version 1.0.0 createTime: 15/6/3 上午9:22
 * @see
 * @since 1.6
 */
@Slf4j
public class ConsumerNoSpringService {


    /**
     * 消费消息（会调用 BusinessProcessInterface 的实现）
     *
     * @param topicInfo topic
     * @param beanName 业务实现类
     * @param traceLogId logId
     */
    public static void consumerMessages(final TopicInfo topicInfo, final Class<? extends BusinessProcessInterface> beanName,final String traceLogId)
            throws InstantiationException, IllegalAccessException {
        log.info("consumer message topic info:{},beanName:{},traceLogId:{}",topicInfo,beanName,traceLogId);
        final BusinessProcessInterface businessProcess = ConsumeMessageManager.getBusinessProcessProxy(topicInfo,beanName);
        ConsumeMessageManager.process(topicInfo,businessProcess,traceLogId);
    }
}
