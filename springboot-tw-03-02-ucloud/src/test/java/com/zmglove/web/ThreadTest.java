package com.zmglove.web;

import lombok.extern.slf4j.Slf4j;

/**
 * 多线程的练习
 *
 * @author CZH
 * @version 1.0
 * @date 2019/9/30 11:27
 **/
public class ThreadTest {

    public static void main(String[] args){
        GoThread thread1 = new GoThread();
        thread1.start();
        thread1.start();

    }
}


@Slf4j
class GoThread extends Thread{

    @Override
    public void run(){
        log.info("............");
    }
}
