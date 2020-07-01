package com.scaffold.common.constant.Enum;

import java.io.File;

/**
 * @Author danyiran
 * @create 2020/7/1 22:13
 */
public enum Separator {

    LINE(System.getProperty("line.separator") != null ? System.getProperty("line.separator") : "\n"),
    FILE(File.separator);

    private String separator;

    private Separator(String separator) {
        this.separator = separator;
    }

    public String val() {
        return this.separator;
    }
}
