package com.zmglove.web.cnanotchange;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 通过不可变的累加器对象进行操作
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/12 16:19
 **/
@Slf4j
public final class IntAcc {
    private final int init;

    public IntAcc(int init) {
        this.init = init;
    }

    public IntAcc(IntAcc acc, int init) {
        this.init = acc.getValue() + init;
    }

    public IntAcc add(int i) {
        return new IntAcc(this, i);
    }

    public int getValue() {
        return this.init;
    }

    public static void main(String[] args) {
        IntAcc safeIntAcc = new IntAcc(0);

        // 定义三个线程
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldValue =  safeIntAcc.getValue();

                int result = safeIntAcc.add(inc).getValue();
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
