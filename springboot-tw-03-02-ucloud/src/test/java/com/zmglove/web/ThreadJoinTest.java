package com.zmglove.web;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 测试线程Join
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 9:58
 **/
@Slf4j
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        // 定义两个线程
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoinTest::create).collect(Collectors.toList());
        // 启动线程
        threads.forEach(Thread::start);

        //执行这两个线程join方法
        for(Thread thread : threads){
            thread.join();
        }

        threads.get(0).join();

        for(int i = 0; i<100;i++){
            log.info("xxxx1---{}",i);
        }
        //main循环输出
        for(int i=0;i<10;i++){
            log.info("{}#{}",Thread.currentThread().getName(),i);
            shortSleep();
        }



    }

    /**
     * 创建线程，每个线程只是简单的进行输出
     *
     * @param seq
     * @return
     */
    private static Thread create(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shortSleep();
                log.info("{}#{}", Thread.currentThread().getName(), i);
            }
        }, String.valueOf(seq));
    }

    /**
     * 小睡眠
     */
    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
