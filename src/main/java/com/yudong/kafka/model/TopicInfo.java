package com.yudong.kafka.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zyd on 14-10-17.
 * Modified by chensuqiang on 15-07-27
 * kafka topic info
 */
@Slf4j
@NoArgsConstructor
@ToString
public class TopicInfo {

    /**
     * 消息类型 同步、异步
     */
    private ProducerType syncFlag;
    /**
     * Topic 名称
     */
    private String topicName;
    /**
     * 消息发送后的应答方式
     */
    private RequiredAck ack;
    /**
     * 消息发送异常后是否可以重复发送（重复时可能消息重复）
     */
    private boolean retries;
    /**
     * 该消费者所属组名
     */
    private String groupName;
    /**
     * zookeeper 地址（例：127.0.0.1:2181）
     */
    private String zkAddress;
    /**
     * 集群地址列表（列：172.17.13.41:9092,172.17.13.42:9092）
     */
    private String brokerList;

    public ProducerType getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(ProducerType syncFlag) {
        this.syncFlag = syncFlag;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public RequiredAck getAck() {
        return ack;
    }

    public void setAck(RequiredAck ack) {
        this.ack = ack;
    }

    public boolean isRetries() {
        return retries;
    }

    public void setRetries(boolean retries) {
        this.retries = retries;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public String getBrokerList() {
        return brokerList;
    }

    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    public TopicInfo(ProducerType syncFlag, String topicName, RequiredAck ack, boolean retries,
                     String groupName, String zkAddress,String brokerList){
        if(groupName == null || groupName.equals("")) {
            groupName = "default_group";
        }
        this.syncFlag = syncFlag ;
        this.topicName = topicName ;
        this.ack = ack ;
        this.retries = retries;
        this.groupName = groupName;
        this.zkAddress = zkAddress;
        this.brokerList = brokerList;
    }

}
