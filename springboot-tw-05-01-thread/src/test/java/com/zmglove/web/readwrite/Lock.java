package com.zmglove.web.readwrite;

/**
 * 一个普通的Lock接口
 */
public interface Lock {
    void lock() throws InterruptedException;

    void unlock();
}
