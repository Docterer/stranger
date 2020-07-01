package com.scaffold.common.util;

import com.scaffold.common.constant.Enum.Symbol;

import java.util.UUID;

/**
 * @Author danyiran
 * @create 2020/7/1 23:44
 */
public class UniqueCodeUtil {

    /**
     * 获取惟一字符串。
     *
     * @return
     */
    public static String initUniqueCode() {
        return UUID.randomUUID().toString().replaceAll(Symbol.DASH.val(), Symbol.NULL.val());
    }
}
