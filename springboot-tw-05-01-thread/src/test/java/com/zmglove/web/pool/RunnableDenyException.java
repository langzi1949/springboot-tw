package com.zmglove.web.pool;

/**
 * 主要用于通知任务提交者，任务队列已无法再接受新的任务。
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 14:13
 **/
public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String message) {
        super(message);
    }
}
