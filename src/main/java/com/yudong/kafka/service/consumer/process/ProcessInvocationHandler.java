package com.yudong.kafka.service.consumer.process;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;



/**
 * Created by zyd on 14-10-19.
 *
 * 代理类
 */
@Slf4j
public class ProcessInvocationHandler implements InvocationHandler {

    private BusinessProcessInterface processInterface;

    public ProcessInvocationHandler(BusinessProcessInterface processInterface){
        this.processInterface = processInterface;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        log.debug("handel: Object:{},method :{},param:{}",processInterface,method,objects);
        return method.invoke(processInterface, objects);
    }
}
