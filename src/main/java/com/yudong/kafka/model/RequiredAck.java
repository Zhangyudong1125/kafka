package com.yudong.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by zyd on 14-10-17.
 * broker 应答方式(消息发送方式为同步时有效)
 */
@Getter
@AllArgsConstructor
public enum RequiredAck {

    NO_ACK("0","无需应答(消息异步))"),
    LEADER_ACK("1","leader应答(同步消息，主节点应答)"),
    ALL_ACK("-1","全部应答(同步消息，主从节点都应答)");

    private String ack;
    private String description;

}
