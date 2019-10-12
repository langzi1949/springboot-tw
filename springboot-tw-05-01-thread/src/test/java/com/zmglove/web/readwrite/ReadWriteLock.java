package com.zmglove.web.readwrite;

/**
 * 读写锁接口
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 17:22
 **/
public interface ReadWriteLock {
    // 创建reader锁
    Lock readLock();

    // 创建write锁
    Lock writeLock();

    // 获取当前有多少线程正在执行写操作
    int getWritingWriters();

    // 获取当前有多少线程正在等待获取写入锁
    int getWaitingWriters();

    // 获取当前有多少线程正在等待获取reader锁
    int getReadingReaders();

    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImp();
    }
    // 工厂方法，创建ReadWriterLock ，并且传入preferWriter
    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImp(preferWriter);
    }
}
