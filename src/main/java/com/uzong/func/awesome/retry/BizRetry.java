package com.uzong.func.awesome.retry;

import com.uzong.func.awesome.Callable;
import lombok.SneakyThrows;

import java.util.Optional;
import java.util.function.Function;


public class BizRetry {


    /**
     * 重试函数，当发生异常时重试指定次数
     */
    public static <T, R> Function<T, R> retry(Function<T, R> f, int maxAttempts) {
        return t -> {
            RuntimeException lastException = null;
            for (int i = 0; i < maxAttempts; i++) {
                try {
                    return f.apply(t);
                } catch (RuntimeException e) {
                    lastException = e;
                }
            }
            assert lastException != null;
            throw lastException;
        };
    }

    /**
     * 错误重试
     *
     * @param callable   调用函数
     * @param retryTimes 调用次数
     */
    @SneakyThrows
    public <R> Optional<R> bizExecuteNeedRetry(Callable<R> callable, int retryTimes) {
        Throwable lastException = null;
        for (int i = 1; i <= retryTimes; i++) {
            try {
                return Optional.ofNullable(callable.call());
            } catch (Throwable exception) {
                lastException = exception;
            }
        }
        if (lastException != null) {
            throw lastException;
        }
        return Optional.empty();
    }

    /**
     * 错误重试
     *
     * @param callable   调用函数
     * @param compensate 补偿函数
     * @param retryTimes 调用次数
     */
    @SneakyThrows
    public <R> Optional<R> bizExecuteNeedRetry(Callable<R> callable, int retryTimes, Callable<R> compensate) {
        for (int i = 1; i <= retryTimes; i++) {
            try {
                return Optional.ofNullable(callable.call());
            } catch (Throwable ignored) {
                // 日志记录
            }
        }
        return Optional.ofNullable(compensate.call());
    }
}
