package com.yudong.kafka.partition;

import kafka.producer.Partitioner;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zyd on 14-10-17.
 * 是在使用第一个分区 (测试用)
 */
@Slf4j
public class FirstPartition implements Partitioner {
    @Override
    public int partition(Object o, int i) {
        //TODO
        log.debug("分区选择：{}，{}",o,i);
        return 0;
    }
}
