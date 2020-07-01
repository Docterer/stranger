package com.scaffold.common.constant.Enum;

/**
 * @Author danyiran
 * @create 2020/7/1 22:14
 */
public enum Symbol {

    NULL(""),
    BLANK(" "),
    COMMA(","),
    COLON(":"),
    SEMICOLON(";"),
    AT("@"),
    STAR("*"),
    PERCENT("%"),
    UNDERSCORE("_"),
    DASH("-"),
    AND("&"),
    SLASH("/"),
    BACKSLASH("\\"),
    VLINE("|"),
    NEWLINE("\n"),
    LEFT_BRACE_BRACKETS("{"),
    RIGHT_BRACE_BRACKETS("}"),
    LEFT_SQUARE_BRACKETS("["),
    RIGHT_SQUARE_BRACKETS("]"),
    LEFT_PAREN_BRACKETS("("),
    RIGHT_PAREN_BRACKETS(")"),
    DOT("."),
    SHARP("#"),
    EQUAL("=");

    private String symbol;

    private Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String val() {
        return this.symbol;
    }
}
