package com.uzong.func.awesome.function;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * 断言工具类增强
 */
public class Predicates {
    
    /**
     * 组合多个断言，全部满足才返回true
     */
    @SafeVarargs
    public static <T> Predicate<T> all(Predicate<T>... predicates) {
        return t -> Arrays.stream(predicates).allMatch(p -> p.test(t));
    }

    /**
     * 组合多个断言，满足一个就返回true
     */
    @SafeVarargs
    public static <T> Predicate<T> any(Predicate<T>... predicates) {
        return t -> Arrays.stream(predicates).anyMatch(p -> p.test(t));
    }
} 