package com.uzong.func.awesome.lazy;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 本文参考来源是阿里公众号开业的文章，工具助手。
 * {@link <a href="https://juejin.cn/post/7028014986265886757">...</a>}
 * <p>
 * 通过 java8 提供的 Supplier 接口，实现惰性价值
 *
 * @param <T>
 */
public class Lazy<T> implements Supplier<T> {

    /**
     * 惰性提供值函数
     */
    private final Supplier<? extends T> supplier;

    private T value;

    private Lazy(Supplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Lazy<T> of(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    @Override
    public T get() {
        if (value == null) {
            T newValue = supplier.get();

            if (newValue == null) {
                throw new IllegalStateException("Lazy value can not be null!");
            }

            value = newValue;
        }

        return value;
    }

    /**
     * 映射转换
     *
     * @param function 函数
     * @return 目标对象值
     */
    public <S> Lazy<S> map(Function<? super T, ? extends S> function) {
        return Lazy.of(() -> function.apply(get()));
    }

    /**
     * 映射转换
     */
    public <S> Lazy<S> flatMap(Function<? super T, Lazy<? extends S>> function) {
        return Lazy.of(() -> function.apply(get()).get());
    }
}
