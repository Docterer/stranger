package com.scaffold.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

/**
 * @Author danyiran
 * @create 2020/7/1 23:25
 */
@Slf4j
public class MessageHelper {

    private MessageHelper() {
    }

    public static String getMessage(String code, Object... args) {
        int length = args.length;
        String[] strArray = new String[length];

        for (int i = 0; i < length; ++i) {
            strArray[i] = String.valueOf(args[i]);
        }

        String msg = SpringHelper.getApplicationContext().getMessage(code, strArray, (String) null, (Locale) null);
        log.trace("getMessage, message: " + msg);
        return msg;
    }
}
