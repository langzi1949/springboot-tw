package com.zmglove.web.notify;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * 需要特别说明的是，当真正的多线程执行的时候，这个Queue依旧是数据不一致，会导致线程不安全。简单的方法可以 将 if 判断都改为while，notify 更改为notifyAll即可。
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 15:26
 **/
@Slf4j
public class EventQueue {

    private final int max;

    static class Event {
    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                console(" the queue is full");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                console(" the queue is empty");
                try {
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console(" the event " + event + " is handled...........");
            return event;
        }
    }

    /**
     * 控制台的输出
     *
     * @param str
     */
    private void console(String str) {
        log.info("{} - {}", Thread.currentThread().getName(), str);
    }
}
