package com.yudong.kafka.service.consumer.process;

import org.springframework.stereotype.Service;

/**
 * Created by zyd on 14/10/24.
 * Modified by chensuqiang on 15/07/28.
 * 业务处理接口
 */
@Service
public abstract class BusinessProcessInterface<V> {

    /**
     * 业务处理
     *
     * @param args          业务处理参数
     */
    public abstract void doBusiness(V args);
}
