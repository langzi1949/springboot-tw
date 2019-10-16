package com.zmglove.web.fork;

import java.util.concurrent.RecursiveTask;

/**
 * @author CZH
 * @version 1.0
 * @date 2019/10/15 10:17
 **/
public class CountTask extends RecursiveTask<Integer> {
    // 阈值
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小，就直接计算任务
        boolean cancompute = (end - start) <= THRESHOLD;
        if (cancompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }

        return sum;
    }
}
