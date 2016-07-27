package com.yudong.disconf;

import com.yudong.kafka.model.TopicInfo;
import com.yudong.kafka.service.producer.ProducerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by zyd on 15-07-28.
 *
 */
@Slf4j
public class ProducerTest extends BaseTest {
    @Autowired
    private ProducerServiceImpl producerService;

    @Autowired
    private TopicInfo topicInfo;

    @Test
    public void testProduceAsyncMsg(){
        try {
            String msg;
            System.out.println("开始发送");
            for (int i = 0 ;i < 100 ; i++){
                msg = "message" + i;
                producerService.sendMessage(topicInfo, msg, String.valueOf(System.currentTimeMillis()));
                System.out.println("Send Message:" + msg);
                log.info(msg);
                Thread.sleep(10000);
            }
            System.out.println("发送完成");
        } catch (InterruptedException e) {
            log.debug("发送异常:{}",e);
        }
    }
}