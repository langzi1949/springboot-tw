package com.zmglove.web.future;

/**
 * Future的实现
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/12 17:51
 **/
public class FutureTask<T> implements Future<T> {

    // 计算结果
    private T result;
    // 任务是否已经完成
    private boolean isDone = false;
    // 定义对象锁
    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK) {
            // 当任务没有完成的时候，调用get方法会挂起
            while (!isDone) {
                LOCK.wait();
            }
            return result;
        }
    }

    @Override
    public boolean done() {
        return this.isDone;
    }


    protected void finish(T result) {
        synchronized (LOCK) {
            // balking设计模式
            if (isDone) {
                return;
            }
            // 计算完成，为result指定结果，并且将isDone设置为true，同时唤醒阻塞中线程
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }
}
