package com.scaffold.common.constant.Enum;

/**
 * @Author danyiran
 * @create 2020/7/1 22:12
 */
public enum Encoding {

    UTF8("UTF-8"),
    GBK("GBK"),
    GB2312("GB2312"),
    ISO88591("ISO-8859-1");

    private String encoding;

    private Encoding(String encoding) {
        this.encoding = encoding;
    }

    public String val() {
        return this.encoding;
    }
}
