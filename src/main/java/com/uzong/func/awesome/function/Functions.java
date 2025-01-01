package com.uzong.func.awesome.function;

import java.util.function.Function;


/**
 * 函数增强
 */
public class Functions {

    /**
     * 组合两个函数，先执行f1，再执行f2
     */
    public static <T, V, R> Function<T, R> compose(Function<T, V> f1, Function<V, R> f2) {
        return f1.andThen(f2);
    }

}
