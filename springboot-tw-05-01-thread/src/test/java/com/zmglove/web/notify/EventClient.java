package com.zmglove.web.notify;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 15:54
 **/
public class EventClient {
    public static void main(String[] args){
        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer").start();

        new Thread(() -> {
            for(;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}
