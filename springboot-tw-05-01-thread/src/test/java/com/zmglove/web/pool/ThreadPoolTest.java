package com.zmglove.web.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 线程池测试
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 15:48
 **/
@Slf4j
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        // 简单的定义下线程池
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

        // 定义20个任务并提交给线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    log.info("线程[{}]正在运行", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (; ; ) {
            log.info("ActiveCount = {}", threadPool.getActiveCount());
            log.info("QueueSize = {}", threadPool.getQueueSize());
            log.info("CoreSize = {}", threadPool.getCoreSize());
            log.info("MaxSize = {}", threadPool.getMaxSize());
            log.info("==============================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
