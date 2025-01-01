package com.uzong.func.awesome;

/**
 * 定义一个通用的API接口
 */
public interface HandleService<T, R> {

    /**
     * 事件处理前置
     */
    default void preHandle(T t) {
    }

    /**
     * 核心处理。需要继承去实现
     */
    R action(T t);

    /**
     * 后置处理事件
     */
    default void afterHandle(T t, R r) {
    }

    /**
     * 抽取核心逻辑
     */
    default R doHandle(T t) {

        preHandle(t);

        R r = action(t);

        afterHandle(t, r);

        return r;
    }
}