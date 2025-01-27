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

    /**
     * 通用的逻辑与操作
     */
    public static <T> Predicate<T> and(Predicate<T>... predicates) {
        Predicate<T> result = t -> true;
        for (Predicate<T> predicate : predicates) {
            result = result.and(predicate);
        }
        return result;
    }

    /**
     * 通用的逻辑或操作
     * @param predicates
     * @return
     * @param <T>
     */
    public static <T> Predicate<T> or(Predicate<T>... predicates) {
        Predicate<T> result = t -> false;
        for (Predicate<T> predicate : predicates) {
            result = result.or(predicate);
        }
        return result;
    }

    /**
     * 通用的逻辑非操作
     * @param predicate
     * @return
     * @param <T>
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }
} 