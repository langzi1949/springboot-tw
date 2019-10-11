package com.zmglove.web.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * 显示锁的测试
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 18:01
 **/
@Slf4j
public class BooleanLockTest {

    // 定义BooleanLock
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        // 直接加锁
        try {
            // 下面一句话是为了测试锁超时情况
            lock.lock(1000);
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            log.info("{} 拿到了锁########", Thread.currentThread());
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();
        // 1.多个线程同时争夺锁
        //定义线程，直接诶全部启动
//        IntStream.range(1,5).mapToObj(i -> new Thread(blt::syncMethod))
//                .forEach(Thread::start);
        // 2.可中断被阻塞的线程
        Thread t1 = new Thread(blt::syncMethod, "T1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t2.interrupt();
    }
}
