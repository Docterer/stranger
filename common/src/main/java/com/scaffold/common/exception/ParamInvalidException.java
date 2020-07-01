package com.scaffold.common.exception;

import com.scaffold.common.constant.Enum.StatusCode;

/**
 * @Author danyiran
 * @create 2020/7/1 23:20
 */
public class ParamInvalidException extends RootException {
    public ParamInvalidException(String s) {
        super(s, StatusCode.SC_400.val());
    }
}
