package com.zmglove.web.future;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * CompletableFutue的测试类
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/14 10:57
 **/
@Slf4j
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的Future
//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            }catch ( InterruptedException e){
//                e.printStackTrace();
//            }
//            log.info("在Future中运行任务");
//        });
//
//        log.info("result  = {}",future.get());
//        log.info("没有返回值的CompletableFuture测试完毕");

        // 有返回值得Future
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//
//            try {
//                TimeUnit.SECONDS.sleep(6);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            log.info("1currentName ={}",Thread.currentThread());
//            return "ZMG";
//        });
//        log.info("result  = {}",future.get());
//        log.info("有返回值的CompletableFuture测试完毕");

        // 使用thenApplyAsync 进行串行操作，但是这些操作是会另起一个线程进行执行。
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//
//            try {
//                TimeUnit.SECONDS.sleep(6);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            log.info("1currentName ={}", Thread.currentThread());
//            return "ZMG";
//        }).thenApplyAsync((name) -> {
//            log.info("2currentName ={}", Thread.currentThread());
//            return "Hello " + name;
//        }).thenApplyAsync((name) -> {
//            log.info("2currentName ={}", Thread.currentThread());
//            return "Hello " + name;
//        }).thenApplyAsync((name) -> {
//            log.info("2currentName ={}", Thread.currentThread());
//            return "Hello " + name;
//        });
//        log.info("result  = {}", future.get());
//        log.info("有返回值的completablefuture测试完毕");

        //使用thenCompose进行调用
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            try{
//                TimeUnit.SECONDS.sleep(4);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            return "ZMG";
//        }).thenCompose(name -> CompletableFuture.supplyAsync(name::length));
//        log.info("result  = {}", future.get());

        log.info("xxxxxxxxxxxxxx");
        // 将两个completableFuture 合并到另外一个Future中执行
//        CompletableFuture<Integer> weightFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("WeightFuture:{}",Thread.currentThread().getName());
//            return 10;
//        });
//
//        CompletableFuture<Double> heightFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("heightFuture:{}",Thread.currentThread().getName());
//            return 9.8;
//        });
//
//        CompletableFuture<String> combineFuture = weightFuture.thenCombine(heightFuture,(t,v) ->{
//            log.info("combineFuture:{}",Thread.currentThread().getName());
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("combineFuture111:{}",Thread.currentThread().getName());
//            return String.valueOf(t).concat(String.valueOf(v));
//        });
//
//        log.info("result = {}",combineFuture.get());

        // 如果是多个Future呢? 三个以上吧  allOf方法
//        List<String> nbaTeamName = Arrays.asList("Lakers","Warriors","Rockets");
//
//        List<CompletableFuture<Integer>> nbaTeamFutures = nbaTeamName.stream()
//                .map(name -> CompletableFuture.supplyAsync(name::length)).collect(Collectors.toList());
//
//        CompletableFuture<Void> allFuture = CompletableFuture.allOf(nbaTeamFutures.toArray(new CompletableFuture[nbaTeamFutures.size()]));
//
//        CompletableFuture<List<Integer>> allFutureResult = allFuture.thenApply(v -> {
//            return nbaTeamFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
//        });
//
//        log.info("result = {}",allFutureResult.get());


        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 7;
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1,future2,future3);
        log.info("result = {}",anyOfFuture.get());


    }
}
