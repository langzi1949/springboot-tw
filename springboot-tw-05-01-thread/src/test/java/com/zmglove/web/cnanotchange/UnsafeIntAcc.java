package com.zmglove.web.cnanotchange;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 非线程安全的累加器
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/12 15:45
 **/
@Slf4j
public class UnsafeIntAcc {

    private int init;

    public UnsafeIntAcc(int init){
        this.init = init;
    }

    /**
     * 加上i
     * @param i
     * @return
     */
    public int add(int i){
        this.init += i;
        return this.init;
    }

    public int getValue(){
        return this.init;
    }

    public static void main(String[] args){
        UnsafeIntAcc unsafeIntAcc = new UnsafeIntAcc(0);

        // 定义三个线程
        IntStream.range(0,3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while(true){
                // 首先获得旧值
                int oldValue = unsafeIntAcc.getValue();
                // 累加
                int result = unsafeIntAcc.add(inc);
                log.info("{} + {} = {}",oldValue,inc,result);
                // 如果不等的话，就输出错误的信息
                if(inc + oldValue != result){
                    log.error("ERROR: {} + {} = {}",oldValue,inc,result);
                }
                inc ++;
                slowly();

            }
        }).start());
    }



    private static void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
