package com.yudong.kafka.thread;

import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.service.nospring.ProducerNoSpringService;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息发送线程
 * <p>
 * </p>
 * User: zyd Date: 15-10-17 ProjectName: kafka-service Version: 1.0
 */
@Slf4j
public class SendMsgThread implements Runnable {

    /**
     * 主题
     */
    private TopicInfo topicInfo;

    /**
     * 消息
     */
    private Object message;

    /**
     * 日志ID
     */
    private String traceLogId;

    /**
     * 消息发送构造函数
     *
     * @param topicInfo 主题
     * @param message   消息
     */
    public SendMsgThread(TopicInfo topicInfo, Object message, String traceLogId) {
        this.topicInfo = topicInfo;
        this.message = message;
        this.traceLogId = traceLogId;
    }

    @Override
    public void run() {
        try {
            ProducerNoSpringService.sendMessage(topicInfo, message, traceLogId);
        } catch (Exception e) {
            log.error("failed to kafka build message, parameter:{} {}, cause：{}", message,
                    Throwables.getStackTraceAsString(e));
        }
    }

}
