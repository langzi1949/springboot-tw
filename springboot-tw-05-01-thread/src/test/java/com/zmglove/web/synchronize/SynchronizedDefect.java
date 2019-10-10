package com.zmglove.web.synchronize;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/10 17:13
 **/
public class SynchronizedDefect {

    public synchronized void syncMethod(){
        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod,"T1");

        t1.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(defect::syncMethod,"T2");
        t2.start();
    }
}
