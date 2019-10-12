package com.zmglove.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 锁消除测试
 *
 * @author CZH
 * @version 1.0
 * @date 2019/9/27 11:32
 **/
@Slf4j
public class LockThinTest {

    private volatile int lock = 0;

    @Test
    public void test(){
        long startTime = System.currentTimeMillis();

        for(int i=0;i<100000;i++){
            new Thread(() ->{
                createString("str1","str2");
                lock++;
            }).start();
        }
        if(lock>=10000000){
            log.info(">>>>>>>>>>>一共耗时:{}毫秒",(System.currentTimeMillis() - startTime));
        }

    }


    private static String createString(String str1,String str2){
        StringBuffer sb = new StringBuffer();
        sb.append(str1);
        sb.append(str2);
        return sb.toString();
    }

    @Test
    public void test1(){
        this.fun1(new Integer(3));
    }


    private void fun1(Integer t){
        log.info("Integer..........");
    }

    private void fun1(int t){
        log.info("int..........");
    }
}
