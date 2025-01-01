package com.uzong.func.awesome.callback;

import com.uzong.func.awesome.CallbackTask;

import java.util.concurrent.CompletableFuture;

public class Invoker {

    /**
     * 借助 CompletableFuture 来实现异步行为。
     * 不会抛出异常，在 onFailure 中处理异常
     *
     * @param executeTask 异步任务调研
     * @param <R>         业务参数类型
     * @return 返回值
     */
    private static <R> CompletableFuture<R> doInvoker(
            CallbackTask<R> executeTask) {
        return CompletableFuture
                .supplyAsync(executeTask::execute)
                .whenComplete((result, throwable) -> {
                    // 不管成功与失败，whenComplete 都会执行，
                    // 通过 throwable == null 跳过执行
                    if (throwable == null) {
                        executeTask.onSuccess(result);
                    }
                })
                .exceptionally(throwable -> {
                    executeTask.onFailure(throwable);
                    // todo 给一个默认值，或者使用 Optional包装一下，使用者应该考虑异常情况
                    return null;
                });
    }
}
