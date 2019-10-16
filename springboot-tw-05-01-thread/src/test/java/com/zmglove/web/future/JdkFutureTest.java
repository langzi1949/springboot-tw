package com.zmglove.web.future;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 这个是JDK的Future的测试
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/14 10:34
 **/
@Slf4j
public class JdkFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {

            TimeUnit.SECONDS.sleep(10);

            return "ZMG";
        });

        new Thread(futureTask).start();

        log.info("xxxxxxxxxxx");
        log.info("result = {}",futureTask.get());

        log.info("xxxxxxxxxxxxxxxxxxxxxxx");

    }


}
