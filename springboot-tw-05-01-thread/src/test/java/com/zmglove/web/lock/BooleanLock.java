package com.zmglove.web.lock;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * 自定义的锁
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 17:36
 **/
@Slf4j
public class BooleanLock implements Lock{
    // 当前拥有锁的线程
    private Thread currentThread;

    // false 代表当前锁没有线程获得或者已经释放了， true 代表被currentThread线程获得
    private boolean locked = false;

    private final List<Thread> blockedList =  new ArrayList<>();


    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while(locked){
                blockedList.add(Thread.currentThread());
                try {
                    this.wait();
                }catch (InterruptedException e){
                    blockedList.remove(Thread.currentThread());
                    throw e;
                }
            }

            blockedList.remove(Thread.currentThread());
            this.locked = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills < 0){
                this.lock();
            }else{
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while(locked){
                    log.info("当前{}线程一直在尝试",Thread.currentThread().getName());
                    if (remainingMills <= 0) {
                        throw  new TimeoutException("不能获取锁:"+mills +"毫秒");
                    }

                    if(!blockedList.contains(Thread.currentThread())){
                        blockedList.add(Thread.currentThread());
                    }
                    try {
                        this.wait(remainingMills);
                    }catch (InterruptedException e){
                        blockedList.remove(Thread.currentThread());
                        throw e;
                    }
                    remainingMills = endMills - System.currentTimeMillis();
                }

                blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread == Thread.currentThread()){
                this.locked = false;
                Optional.of(Thread.currentThread().getName() + " 释放锁").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
