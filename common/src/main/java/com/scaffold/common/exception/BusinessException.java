package com.scaffold.common.exception;

import com.scaffold.common.constant.Enum.StatusCode;

/**
 * @Author danyiran
 * @create 2020/7/1 23:21
 */
public class BusinessException extends RootException {

    public BusinessException(String s) {
        super(s, StatusCode.SC_200.val());
    }
}
