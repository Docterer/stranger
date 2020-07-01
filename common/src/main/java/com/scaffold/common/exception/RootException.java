package com.scaffold.common.exception;

/**
 * @Author danyiran
 * @create 2020/7/1 23:18
 */
public class RootException extends RuntimeException {

    protected String code;

    public RootException(String s, String code) {
        super(s);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
