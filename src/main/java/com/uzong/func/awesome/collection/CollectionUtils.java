package com.uzong.func.awesome.collection;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class CollectionUtils {

    /**
     * 过滤集合
     *
     * @param collection 原始集合
     * @param predicate  过滤条件
     * @param <T>        集合元素类型
     * @return 过滤后的集合
     */
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream()
                         .filter(predicate)
                         .collect(Collectors.toList());
    }

    /**
     * 将集合转换为另一种类型的集合
     *
     * @param collection 原始集合
     * @param mapper     转换函数
     * @param <T>        原始集合元素类型
     * @param <R>        目标集合元素类型
     * @return 转换后的集合
     */
    public static <T, R> List<R> map(Collection<T> collection, Function<T, R> mapper) {
        return collection.stream()
                         .map(mapper)
                         .collect(Collectors.toList());
    }

    /**
     * 将集合分组
     *
     * @param collection 原始集合
     * @param classifier 分组函数
     * @param <T>        集合元素类型
     * @param <K>        分组键类型
     * @return 分组后的Map
     */
    public static <T, K> Map<K, List<T>> groupBy(Collection<T> collection, Function<T, K> classifier) {
        return collection.stream()
                         .collect(Collectors.groupingBy(classifier));
    }


}