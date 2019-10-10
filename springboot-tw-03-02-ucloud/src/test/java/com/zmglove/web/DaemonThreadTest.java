package com.zmglove.web;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程的学习
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/8 16:45
 **/
public class DaemonThreadTest {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true); // 加上这个注释的时候，JVM会正常退出，要不然会JVM会一直运行着
        /**
         * JVM正常退出的条件是，没有一个非守护线程
         */
        thread.start();
        Thread.sleep(2_000L);
        System.out.println("主线程结束生命周期");
    }
}
