package com.uzong.func.awesome.guard;

import com.uzong.func.awesome.Callable;

import java.util.Optional;


/**
 * 异常救生员，如果出现异常时，我们总希望可以调用一些补救的方法。比如发生主键冲突时，希望可以执行更新方法
 * <p>
 * >> 当一些错误发生时后，应该做一些补救措施，这个类就是打算干这个的
 */
public class ExceptionGuard {


    public static final String DUPLICATE_ENTRY = "Duplicate entry";

    /**
     * 使用案例
     * </code>
     *
     * @param first 正常调用
     * @param after 错误发生时调用
     */
    public static <R> R exceptionRescue(Callable<R> first, Callable<R> after) throws Exception {
        try {
            return first.call();
        } catch (Throwable exception) {
            return after.call();
        }
    }


    /**
     * 当插入失败时候，进行更新处理。常用于处理并发，也可以直接使用exceptionRescue
     *
     * @param insert 插入
     * @param update 更新
     * @return 业务值
     */
    public static <R, T> R duplicate(Callable<R> insert, Callable<R> update) {
        try {
            return insert.call();
        } catch (Exception e) {
            Boolean resultBoolean = Optional.of(e)
                    .map(err ->
                            Optional.of(err)
                                    .map(Throwable::getMessage)
                                    .map(message -> message.contains(DUPLICATE_ENTRY))
                                    .orElse(Boolean.FALSE)
                                    ||
                                    Optional.of(err)
                                            .map(Throwable::getCause)
                                            .map(Throwable::getMessage)
                                            .map(message -> message.contains(DUPLICATE_ENTRY))
                                            .orElse(Boolean.FALSE)
                    ).orElse(Boolean.FALSE);
            if (resultBoolean) {
                return update.call();
            }
            throw e;
        }
    }
}
