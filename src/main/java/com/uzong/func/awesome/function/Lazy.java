package com.uzong.func.awesome.function;

/**
 * @author sky
 * @since 2025/1/1
 */

import lombok.Getter;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 延迟计算工具类
 */
public class Lazy<T> {
    private Supplier<T> supplier;
    private T value;
    /**
     * -- GETTER --
     *  判断是否已经初始化
     */
    @Getter
    private boolean initialized = false;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }

    /**
     * 获取值，第一次调用时才会计算
     */
    public T get() {
        if (!initialized) {
            value = supplier.get();
            // 释放supplier引用
            supplier = null;
            initialized = true;
        }
        return value;
    }

    /**
     * 映射转换
     */
    public <R> Lazy<R> map(Function<T, R> mapper) {
        return Lazy.of(() -> mapper.apply(get()));
    }
}
