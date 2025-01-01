package com.uzong.func.awesome;

@FunctionalInterface
public interface Callable<V> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     */
    V call();
}