package com.scaffold.common.dto;

import java.io.Serializable;

/**
 * @Author danyiran
 * @create 2020/7/1 23:57
 */
public class WsMessageShowDto implements Serializable {

    private String type;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WsMessageShowDto{");
        sb.append("type='").append(type).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
