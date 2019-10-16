package com.zmglove.web.block;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 使用Condition的方式实现一个阻塞队列
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/16 13:31
 **/
@Slf4j
public class ConditionTest {

    public static void main(String[] args) {
        // 先创建一个阻塞队列
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
        IntStream.range(0, 10).mapToObj(i -> new Thread(() -> {
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
class BlockQueue<E> {
    final Lock lock = new ReentrantLock();

    // 存数据的线程等待集 -因为满了需要等待
    final Condition notFull = lock.newCondition();
    // 取数据的线程等待集 -因为空了需要等待
    final Condition notEmpty = lock.newCondition();
    // 用传统的List进行存放数据
    final List<E> list;

    private final int maxSize;

    public BlockQueue(int maxSize) {
        this.maxSize = maxSize;
        this.list = new ArrayList<>();
    }

    /**
     * 存放数据
     *
     * @param e
     * @throws InterruptedException
     */
    public void put(E e) throws InterruptedException {
        lock.lock();
        log.info("线程[{}]准备写入数据:{}", Thread.currentThread().getName(), e);
        try {
            while (list.size() >= maxSize) {
                log.warn("线程[{}]写入数据:{}异常，缓冲池已经满了", Thread.currentThread().getName(), e);
                notFull.await();
            }
            // 存放数据
            list.add(e);
            // 唤醒其他线程进行取
            notEmpty.signalAll();

        } finally {
            lock.unlock();
        }
    }


    /**
     * 读取时间
     *
     * @return
     * @throws InterruptedException
     */
    public E take() throws InterruptedException {
        lock.lock();

        try {
            log.info("线程[{}] 准备取数据", Thread.currentThread().getName());
            while (list.size() == 0) {
                log.error("线程[{}]读取数据异常，缓冲池已经空了", Thread.currentThread().getName());
                notEmpty.await();
            }
            E e = list.remove(0);
            notFull.signalAll();
            return e;
        } finally {
            lock.unlock();
        }
    }

}