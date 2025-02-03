package com.uzong.func.awesome.base;

import java.util.function.Function;

/**
 * 封装两种可能结果（左值通常为错误，右值为成功）
 */
public class Either<L, R> {
    private final L left;
    private final R right;
    private final boolean isLeft;

    private Either(L left, R right, boolean isLeft) {
        this.left = left;
        this.right = right;
        this.isLeft = isLeft;
    }

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(value, null, true);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(null, value, false);
    }

    public boolean isLeft() {
        return isLeft;
    }

    public L getLeft() {
        if (!isLeft) {
            throw new IllegalStateException("Not a left value");
        }
        return left;
    }

    public R getRight() {
        if (isLeft) {
            throw new IllegalStateException("Not a right value");
        }
        return right;
    }

    public <T> T fold(Function<L, T> leftFunc, Function<R, T> rightFunc) {
        return isLeft ? leftFunc.apply(left) : rightFunc.apply(right);
    }

    public <T> Either<L, T> map(Function<R, T> mapper) {
        return isLeft ? left(left) : right(mapper.apply(right));
    }

    public <T> Either<L, T> flatMap(Function<R, Either<L, T>> mapper) {
        return isLeft ? left(left) : mapper.apply(right);
    }
}