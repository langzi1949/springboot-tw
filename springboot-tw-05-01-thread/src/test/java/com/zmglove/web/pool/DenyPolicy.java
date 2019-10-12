package com.zmglove.web.pool;

/**
 * 拒绝策略
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/11 14:05
 **/
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * 该拒绝策略会直接放弃任务，啥也不做
     */
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // 啥也不做
        }
    }

    /**
     * 该拒绝策略会向任务提交者抛出异常
     */
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("The runnable " + runnable + "will be abort.....");
        }
    }

    /**
     * 该拒绝策略会是任务在提交者所在的线程中执行任务
     */
    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
