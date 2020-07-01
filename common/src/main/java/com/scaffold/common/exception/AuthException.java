package com.scaffold.common.exception;

import com.scaffold.common.constant.Enum.StatusCode;

/**
 * @Author danyiran
 * @create 2020/7/1 23:22
 */
public class AuthException extends RootException {
    public AuthException(String s) {
        super(s, StatusCode.SC_401.val());
    }
}
