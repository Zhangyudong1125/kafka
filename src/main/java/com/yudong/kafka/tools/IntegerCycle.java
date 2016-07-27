package com.yudong.kafka.tools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zyd on 14-10-19.
 * 整数循环
 */
@Slf4j
public class IntegerCycle {

    /**
     * (0 到 endNum 循环 每次加1)
     * @param endNum 循环的最大数
     * @return 循环的索引
     */
    public static int getIndex(AtomicInteger atomicInteger,int endNum){
        if(atomicInteger.get()  >= endNum -1){
            return atomicInteger.getAndSet(0);
        }
        return atomicInteger.getAndIncrement();
    }
}
