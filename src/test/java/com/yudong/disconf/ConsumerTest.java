package com.yudong.disconf;

import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.service.consumer.ConsumerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by zyd on 15-07-28.
 *
 */
@Slf4j
@Service
public class ConsumerTest extends BaseTest {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @Autowired
    private TopicInfo topicInfo;

    @Test
    public void consumer(){
        System.out.println("开始消费");
        consumerService.consumerMessages(topicInfo, BusinessProcesser.class, UUID.randomUUID().toString());
        try {
            Object lock = new Object();
            synchronized (lock) {
                while (true) {
                    lock.wait();
                }
            }
        } catch (InterruptedException e) {
            log.error("中断异常:{}", e);
        }
    }

}