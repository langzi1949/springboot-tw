package com.zmglove.web.cnanotchange;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 线程安全的累加器,但是是通过同步块，如果在add方法和getValue方法，两个原子操作在一起不代表原子性
 *
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/12 15:45
 **/
@Slf4j
public class SafeIntAcc {

    private int init;

    public SafeIntAcc(int init) {
        this.init = init;
    }

    /**
     * 加上i
     *
     * @param i
     * @return
     */
    public int add(int i) {
        this.init += i;
        return this.init;
    }

    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        SafeIntAcc safeIntAcc = new SafeIntAcc(0);

        // 定义三个线程
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue;
                int result;
                // 首先获得旧值
                synchronized (SafeIntAcc.class) {
                    oldValue = safeIntAcc.getValue();
                    // 累加
                    result = safeIntAcc.add(inc);
                }
                log.info("{} + {} = {}", oldValue, inc, result);
                // 如果不等的话，就输出错误的信息
                if (inc + oldValue != result) {
                    log.error("ERROR: {} + {} = {}", oldValue, inc, result);
                }
                inc++;
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
