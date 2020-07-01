package com.scaffold.common.constant.Enum;

/**
 * @Author danyiran
 * @create 2020/7/1 22:11
 */
public enum DeleteFlagEnum {

    SIMPLE('0', "状态正常"),
    DELETED('1', "状态已删除");

    private char deleteFlag;
    private String desc;

    DeleteFlagEnum(char deleteFlag, String desc) {
        this.deleteFlag = deleteFlag;
        this.desc = desc;
    }

    public char deleteFlag() {
        return deleteFlag;
    }

    public String desc() {
        return desc;
    }

}
