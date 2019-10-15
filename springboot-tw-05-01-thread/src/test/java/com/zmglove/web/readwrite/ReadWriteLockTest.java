package com.zmglove.web.readwrite;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 读写锁测试
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 18:30
 **/
@Slf4j
public class ReadWriteLockTest {

    private final static String text = "ThisisExample4ReadWriteLock";

    public static void main(String[] args) throws InterruptedException {
        final ShareData shareData = new ShareData(50);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int index = 0; index < text.length(); index++) {
                    try {
                        char c = text.charAt(index);
                        shareData.write(c);
                        log.info("线程[{}]写入:{}", Thread.currentThread().getName(), c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        TimeUnit.MINUTES.sleep(1);
        log.info("数据值:{}",new String(shareData.read()));

//
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                while (true) {
//                    try {
//                        log.info("线程[{}]读取数据:{}", Thread.currentThread().getName(), shareData.read());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
    }
}
