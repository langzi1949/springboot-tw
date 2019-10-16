package com.zmglove.web.block;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 使用传统的wait-notify方式进行
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/16 14:17
 **/
@Slf4j
public class BlockDemo {

    public static void main(String[] args){
        MyBlockQueue<String> queue = new MyBlockQueue<>(5);

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


@Slf4j
class MyBlockQueue<E> {
    // 首先定义一个list,用来存放数据
    private final List<E> list;

    private final int maxSize;

    // 定义一个锁
    private Object lock = new Object();


    public MyBlockQueue(int maxSize) {
        this.maxSize = maxSize;
        list = new ArrayList<>();
    }


    /**
     * 存放数据
     *
     * @param e
     * @throws InterruptedException
     */
    public void put(E e) throws InterruptedException {
        log.info("线程[{}]准备写入数据:{}", Thread.currentThread().getName(), e);
        synchronized (lock) {
            while (list.size() >= maxSize) {
                //
                log.warn("线程[{}]写入数据:{} 出现异常，缓存池已经满了", Thread.currentThread().getName(), e);
                lock.wait();
            }
            list.add(e);
            lock.notifyAll();
        }
    }


    /**
     * 读取数据
     *
     * @return
     * @throws InterruptedException
     */
    public E take() throws InterruptedException {
        log.info("线程[{}]准备读取数据", Thread.currentThread().getName());
        synchronized (lock) {
            while (list.size() == 0) {
                //
                log.warn("线程[{}]读取数据:{} 出现异常，缓存池已经空了", Thread.currentThread().getName());
                lock.wait();
            }
            E e = list.remove(0);
            lock.notifyAll();
            return e;
        }
    }
}
