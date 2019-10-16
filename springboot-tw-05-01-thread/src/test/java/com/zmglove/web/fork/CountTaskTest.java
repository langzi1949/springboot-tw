package com.zmglove.web.fork;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/15 10:47
 **/
@Slf4j
public class CountTaskTest {


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4
        CountTask task = new CountTask(1,5);

        // 执行一个任务
        Future<Integer> result = pool.submit(task);

        try {
            log.info("result = {}",result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
