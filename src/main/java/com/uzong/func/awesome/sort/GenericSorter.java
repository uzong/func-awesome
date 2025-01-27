package com.uzong.func.awesome.sort;

import java.util.*;
import java.util.function.Function;

public class GenericSorter {

    /**
     * 通用的排序器
     *
     * @param collection 原始集合
     * @param keyExtractor 排序键提取函数
     * @param <T>        集合元素类型
     * @param <U>        排序键类型
     * @return 排序后的集合
     */
    public static <T, U extends Comparable<U>> List<T> sort(Collection<T> collection, Function<T, U> keyExtractor) {
        List<T> result = new ArrayList<>(collection);
        result.sort(Comparator.comparing(keyExtractor));
        return result;
    }
}