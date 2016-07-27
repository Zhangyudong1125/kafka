package com.yudong.disconf;

import com.yudong.kafka.service.consumer.process.BusinessProcessInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by zyd on 15-07-28.
 * 业务逻辑处理类
 */
@Slf4j
@Service
public class BusinessProcesser extends BusinessProcessInterface<String> {
    @Override
    public void doBusiness(String message) {
        System.out.println("Received Message:" + message);
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
