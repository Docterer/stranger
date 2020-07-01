package com.scaffold.common.exception;

import com.scaffold.common.constant.Enum.StatusCode;

/**
 * @Author danyiran
 * @create 2020/7/1 23:19
 */
public class ServerException extends RootException {
    public ServerException(String s) {
        super(s, StatusCode.SC_500.val());
    }
}
