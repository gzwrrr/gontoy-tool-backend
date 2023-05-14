package com.gontoy.util;

import com.gontoy.lang.Nullable;

/**
 * 断言
 */
public class Asset {

    /**
     * 判空
     * @param object 任意对象
     * @param message 异常信息
     */
    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
