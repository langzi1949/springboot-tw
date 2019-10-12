package com.zmglove.web.future;

/**
 * 提供给调用者实现计算逻辑之用
 *
 * @author CZH
 * @version 1.0
 * @date 2019/10/12 17:38
 **/
@FunctionalInterface
public interface Task<IN, OUT> {
    OUT get(IN input);
}
