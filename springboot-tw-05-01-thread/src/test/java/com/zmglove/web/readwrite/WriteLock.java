package com.zmglove.web.readwrite;

/**
 * 写入锁 包可见
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 17:39
 **/
class WriteLock implements Lock {
    private final ReadWriteLockImp readWriterLock;

    public WriteLock(ReadWriteLockImp readWriterLock) {
        this.readWriterLock = readWriterLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriterLock.getMutex()) {
            try {
                // 首先使等待获取写入锁的数字+1
                readWriterLock.incrementWaitingWriters();
                // 如果此时有其他线程正在进行读操作，或者写操作，那么当前线程将被挂起
                while (readWriterLock.getReadingReaders() > 0
                        || readWriterLock.getWritingWriters() > 0) {
                    readWriterLock.getMutex().wait();
                }
            } finally {
                // 成功获取到了写入锁，使得等待获取写入锁的计数器减一
                this.readWriterLock.decrementWaitingWriters();
            }
            // 将正在写入的线程数+1
            this.readWriterLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriterLock.getMutex()) {
            // 减少正在写入的锁的线程计数器
            readWriterLock.decrementWritingWriters();
            // 将偏好修改为false
            readWriterLock.changePrefer(false);
            // 唤醒
            readWriterLock.getMutex().notifyAll();
        }
    }
}
