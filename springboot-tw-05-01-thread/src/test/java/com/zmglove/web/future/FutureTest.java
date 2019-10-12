package com.zmglove.web.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 测试类
 */
@Slf4j
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {

        /********************************/
        // 定义不需要返回值的 FutureService
//        FutureService<Void, Void> service = FutureService.newService();
//
//        Future<?> future = service.submit(() -> {
//            try{
//                TimeUnit.SECONDS.sleep(10);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            log.info("结束啦.............");
//        });
//        // get 方法会使当前线程进入阻塞
//        future.get();

        /*******************************/
        FutureService<String,Integer> service = FutureService.newService();

        // submit 方法会立即返回
        Future<Integer> future = service.submit(input -> {

            try{
                TimeUnit.SECONDS.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return input.length();
        },"Hello");

        log.info("result = {}",future.get());
    }
}
