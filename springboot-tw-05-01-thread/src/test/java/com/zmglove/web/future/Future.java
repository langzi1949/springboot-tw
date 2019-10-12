package com.zmglove.web.future;

/**
 * Future接口
 */
public interface Future<T> {

    // 返回计算后的结果，该方法会陷入阻塞状态
    T get() throws InterruptedException;

    // 判断任务有没有完成
    boolean done();
}
