package com.zmglove.web.pool;

/**
 * 创建线程的工厂接口
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 14:03
 **/
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
