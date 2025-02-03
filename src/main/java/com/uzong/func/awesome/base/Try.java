package com.uzong.func.awesome.base;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 封装可能失败的操作，支持链式处理和异常恢复
 */
public class Try<T> {
    private final T result;
    private final Throwable exception;

    private Try(Supplier<T> supplier) {
        T tmpResult = null;
        Throwable tmpEx = null;
        try {
            tmpResult = supplier.get();
        } catch (Throwable e) {
            tmpEx = e;
        }
        this.result = tmpResult;
        this.exception = tmpEx;
    }

    public static <T> Try<T> of(Supplier<T> supplier) {
        return new Try<>(supplier);
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public T get() throws Throwable {
        if (exception != null) {
            throw exception;
        }
        return result;
    }

    public T getOrElse(T defaultValue) {
        return isSuccess() ? result : defaultValue;
    }

    public <R> Try<R> map(Function<T, R> mapper) {
        return isSuccess() ? Try.of(() -> mapper.apply(result)) : failure(exception);
    }

    public <R> Try<R> flatMap(Function<T, Try<R>> mapper) {
        if (!isSuccess()) {
            return failure(exception);
        }
        try {
            return mapper.apply(result);
        } catch (Throwable e) {
            return failure(e);
        }
    }

    public Try<T> recover(Function<Throwable, T> recovery) {
        return isSuccess() ? this : Try.of(() -> recovery.apply(exception));
    }

    private static <T> Try<T> failure(Throwable e) {
        return new Try<>(() -> { throw e instanceof RuntimeException ? (RuntimeException)e : new RuntimeException(e); });
    }
}