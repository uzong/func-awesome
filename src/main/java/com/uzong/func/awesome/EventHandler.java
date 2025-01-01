package com.uzong.func.awesome;

public interface EventHandler<T> {

    /**
     * 事件处理器
     *
     * @param event 事件
     */
    void handle(T event);
}
