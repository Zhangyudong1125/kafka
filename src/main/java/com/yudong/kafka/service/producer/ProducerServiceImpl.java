package com.yudong.kafka.service.producer;

import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.service.nospring.ProducerNoSpringService;
import com.yudong.kafka.thread.SendMsgThread;
import com.yudong.kafka.thread.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by zyd
 * kafka 生产发送消息
 */
@Service
@Slf4j
public class ProducerServiceImpl  {

    /**
     * 发送消息集合
     * @param topicInfo topic 信息
     * @param messages   待发送消息
     * @param <T>       消息类型
     */
    public <T> void sendMessages(TopicInfo topicInfo, List<T> messages, String traceLogId) {
        log.debug("traceLogId:{},topicInfo:{} messages to send :{}", traceLogId, topicInfo,messages);
        ProducerNoSpringService.sendMessages(topicInfo,messages,traceLogId);
    }

    /**
     * 发送单个消息
     * @param topicInfo topic 信息
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public <T> void sendMessage(TopicInfo topicInfo, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo,message);
        ProducerNoSpringService.sendMessage(topicInfo, message, traceLogId);
    }

    /**
     * 异步线程池发送消息
     * @param topicInfo topic 信息
     * @param message   待发送消息
     * @param <T>       消息类型
     */
    public <T> void sendAsyMessage(TopicInfo topicInfo, T message, String traceLogId) {
        log.debug("traceLogId:{} topic：{} message to send :{}", traceLogId, topicInfo,message);
        SendMsgThread sendMsgThread = new SendMsgThread(topicInfo,message,traceLogId);
        ThreadPool.getInstance().getPool().execute(sendMsgThread);
    }


}