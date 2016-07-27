package com.yudong.kafka.service.consumer;

import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.service.consumer.process.BusinessProcessInterface;
import com.yudong.kafka.service.nospring.manager.ConsumeMessageManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


/**
 * Created by zyd on 14-10-19.
 * kafka 消息消费实现
 */
@Service
@Slf4j
public class ConsumerServiceImpl {

    @Autowired
    private ApplicationContext context;

    /**
     * 消费消息（会调用 BusinessProcessInterface 的实现）
     *
     * @param topicInfo topic
     * @param beanName 业务实现类
     * @param traceLogId logId
     */
    public void consumerMessages(final TopicInfo topicInfo, final Class<? extends BusinessProcessInterface> beanName, final String traceLogId) {
        log.info("consumer message topic info:{},beanName:{},traceLogId:{}",topicInfo,beanName,traceLogId);
        final BusinessProcessInterface businessProcess = context.getBean(beanName);
        ConsumeMessageManager.process(topicInfo,businessProcess,traceLogId);
        log.info("process done.");
    }
}
