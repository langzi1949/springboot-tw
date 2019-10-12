package com.zmglove.web.readwrite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 共享的数据信息
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 18:23
 **/
public class ShareData {
    // 定义共享数据
    private final List<Character> container = new ArrayList<>();

    // 构造ReadWriteLock
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();
    // 创建读取锁
    private final Lock readLock = readWriteLock.readLock();
    // 创建写入锁
    private final Lock writeLock = readWriteLock.writeLock();

    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add(i, 'c');
        }
    }

    public char[] read() throws InterruptedException {
        try {
            readLock.lock();
            char[] newBuffer = new char[length];
            for (int i = 0; i < length; i++) {
                newBuffer[i] = container.get(i);
            }
            slowly();
            return newBuffer;
        } finally {
            readLock.unlock();
        }
    }


    public void write(char c) throws InterruptedException {
        try {

            writeLock.lock();
            this.container.add(c);

            slowly();
        } finally {
            writeLock.unlock();
        }
    }

    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
