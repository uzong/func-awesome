package com.uzong.func.awesome;

/**
 * 空对象
 */
public final class None  {

    private static final None INSTANCE = new None();

    public boolean isEmpty() {
        return true;
    }
}
