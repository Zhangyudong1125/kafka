package com.yudong.kafka.model;

import lombok.Getter;

/**
 * Created by zyd on 14-10-17.
 * 消息同步、异步标示
 */
@Getter
public enum ProducerType {
    SYNC("sync","同步消息"),
    ASYNC("async","异步消息");

    private String syncFlag;
    private String description;

    ProducerType(String syncFlag,String description){
        this.syncFlag = syncFlag;
        this.description = description;
    }
}
