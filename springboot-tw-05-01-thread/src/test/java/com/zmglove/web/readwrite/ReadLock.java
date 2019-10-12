package com.zmglove.web.readwrite;

/**
 * 读锁的实现，包可见
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 17:37
 **/
class ReadLock implements Lock {

    private final ReadWriteLockImp readWriterLock;

    public ReadLock(ReadWriteLockImp readWriterLock) {
        this.readWriterLock = readWriterLock;
    }

    @Override
    public void lock() throws InterruptedException {
        // 使用Mutex作为锁
        synchronized (readWriterLock.getMutex()) {
            // 若此时有线程在进行写操作，或者有写线程在等待并且偏向写锁的标志为true时，就会无法获得读锁，只能挂起
            while (readWriterLock.getWritingWriters() > 0
                    || (readWriterLock.getPreferWriter() && readWriterLock.getWritingWriters() > 0)) {
                readWriterLock.getMutex().wait();
            }
            // 成功获得读锁，并且使readingReaders的数量增加
            readWriterLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriterLock.getMutex()) {
            // 释放锁的过程就是使得当前reading的数量减一
            // 将perferWriter设置为true，可以使得writer线程获得更多的机会
            // 通知唤醒线程
            readWriterLock.decrementReadingReaders();
            readWriterLock.changePrefer(true);
            readWriterLock.getMutex().notifyAll();
        }
    }
}
