package com.zmglove.web.block;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 阻塞队列的测试类
 */
@Slf4j
public class BlockQueueTest {

    public static void main(String[] args) {
        // 这个是 wait-notify方式
//        MyBlockQueue<String> queue = new MyBlockQueue<>(5);
        // 这个是 Condition 方式
        BlockQueue<String> queue = new BlockQueue<>(5);

        // 创建10个线程进行写入
        IntStream.range(0, 10).mapToObj(i -> new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                queue.put(String.valueOf(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "PUT-" + i)).forEach(Thread::start);

        // 使用10个线程进行取值
        IntStream.range(0, 3).mapToObj(i -> new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                log.info("线程 [{}] 取值为------------>{}", Thread.currentThread().getName(), queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "GET-" + i)).forEach(Thread::start);
    }
}
