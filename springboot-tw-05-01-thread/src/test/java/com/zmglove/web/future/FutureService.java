package com.zmglove.web.future;

/**
 * 主要用于提交任务，提交的任务主要有两种方式，第一种，不需要返回值，第二种则返回最终的计算结果
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/12 17:28
 **/
public interface FutureService<IN, OUT> {

    // 提交不需要返回值的任务，Future.get方法返回的将是null
    Future<?> submit(Runnable runnable);

    // 提交需要返回值的任务，其中Task接口代替了Runnable接口
    Future<OUT> submit(Task<IN, OUT> task, IN input);

    //使用static方法创建一个实例
    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }

}
