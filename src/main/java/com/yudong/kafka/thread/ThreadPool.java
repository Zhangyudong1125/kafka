package com.yudong.kafka.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池实例
 * <p>
 * </p>
 * User: zyd Date: 15-10-16 ProjectName: kafka-service Version: 1.0
 */
public class ThreadPool {

    private static ThreadPool instance;

    /** 初始线程处理数  */
    private int coreSize = 20;

    /** 最大线程数 */
    private int maxSize = 200;

    /** 最大队列数 */
    private int queueSize = 2000;

    /** 一天秒数 */
    private static final long ONE_DAY_SEC = 24 * 60 * 60;

    /** 空闲线程最大闲置时间 */
    private long keepAliveTime = ONE_DAY_SEC;

    /** 线程池 */
    private ThreadPoolExecutor pool;

    public static ThreadPool getInstance(){
        if(null == instance){
            instance = new ThreadPool();
        }
        return instance ;
    }

    public ThreadPool(){
        init();
    }

    public synchronized  void init(){
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(queueSize);
        if(null == pool){
            pool = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.SECONDS, queue);
        }
    }

    public ThreadPoolExecutor getPool() {
        return pool;
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

}
