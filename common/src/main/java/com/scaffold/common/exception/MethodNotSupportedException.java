package com.scaffold.common.exception;

import com.scaffold.common.constant.Enum.StatusCode;

/**
 * @Author danyiran
 * @create 2020/7/1 23:20
 */
public class MethodNotSupportedException extends RootException {
    public MethodNotSupportedException(String s) {
        super(s, StatusCode.SC_405.val());
    }
}
