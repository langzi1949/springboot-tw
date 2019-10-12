package com.zmglove.web.future;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/12 17:50
 **/
public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    // 为执行的线程指定名字前缀
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";

    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            // 执行任务结束后将null作为结果返回给futureTask
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() -> {
            OUT result = task.get(input);
            // 任务执行结束后，将真实的结果通过finish方法传递给future
            futureTask.finish(result);
        }).start();
        return futureTask;
    }
}
