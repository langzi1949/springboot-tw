package com.zmglove.web.readwrite;

/**
 * 设计为包可见
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 17:26
 **/
class ReadWriteLockImp implements ReadWriteLock {

    // 定义对象锁
    private final Object MUTEX = new Object();

    // 当前有多少线程正在写入
    private int writingWriters = 0;
    // 当前有多少线程正在等待写入
    private int waitingWriters = 0;
    // 当前有多少线程正在read
    private int readingReaders = 0;

    // read和writer的偏好设置
    private boolean preferWriter;

    public ReadWriteLockImp() {
        this(true);
    }

    public ReadWriteLockImp(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    /**
     * 获取对象锁
     *
     * @return
     */
    Object getMutex() {
        return this.MUTEX;
    }

    /**
     * 获取当前是否偏向写锁
     *
     * @return
     */
    boolean getPreferWriter() {
        return preferWriter;
    }


    /**
     * 写入线程的数量增加
     */
    void incrementWritingWriters() {
        this.writingWriters++;
    }

    /**
     * 等待写入的线程数量增加
     */
    void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    /**
     * 读线程的数量增加
     */
    void incrementReadingReaders() {
        this.readingReaders++;
    }

    /**
     * 写入线程的数量减少
     */
    void decrementWritingWriters() {
        this.writingWriters--;
    }

    /**
     * 等待写入的线程数量减少
     */
    void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    /**
     * 读线程的数量增加
     */
    void decrementReadingReaders() {
        this.readingReaders--;
    }

    /**
     * 修改写锁偏好
     *
     * @param preferWriter
     */
    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
