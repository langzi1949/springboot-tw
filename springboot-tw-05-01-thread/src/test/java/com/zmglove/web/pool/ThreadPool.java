package com.zmglove.web.pool;

/**
 * 定义了一个线程池应该俱备的基本操作和方法
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 13:54
 **/
public interface ThreadPool {
    // 提交任务到线程池
    void execute(Runnable runnable);

    // 关闭线程池
    void shutdown();

    // 获取线程池的初始化大小
    int getInitSize();

    // 获取线程池的最大线程数
    int getMaxSize();

    // 获取线程池的核心线程数量
    int getCoreSize();

    // 获取线程池中用于缓存队列的大小
    int getQueueSize();

    //获取线程池中活跃线程的数量
    int getActiveCount();

    // 查看线程池是否已经被关闭
    boolean isShutdown();
}
