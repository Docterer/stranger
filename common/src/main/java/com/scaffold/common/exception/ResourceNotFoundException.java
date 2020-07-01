package com.scaffold.common.exception;

import com.scaffold.common.constant.Enum.StatusCode;

/**
 * @Author danyiran
 * @create 2020/7/1 23:20
 */
public class ResourceNotFoundException extends RootException {
    public ResourceNotFoundException(String s) {
        super(s, StatusCode.SC_404.val());
    }
}
