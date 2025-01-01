package com.uzong.func.awesome;


/**
 * 业务接口，运行设计成功和失败回调。特别适合任务的处理。
 *
 * @param <R>
 */
public interface CallbackTask<R> {
    /**
     * 执行弥补方法
     *
     * @return R
     */
    R execute();

    /**
     * 成功后的回调
     */
    default void onSuccess(R r) {
    }

    /**
     * 失败后的回调
     */
    default void onFailure(Throwable t) {
    }
}
