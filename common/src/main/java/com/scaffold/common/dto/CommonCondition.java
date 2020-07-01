package com.scaffold.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author danyiran
 * @create 2020/7/1 23:33
 */
@Data
public class CommonCondition<T> implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    private String userId;

    private String orgId;

    private String roleId;

    private String startTime;

    private String endTime;

    public String keyword;

    public List<T> conditions;
}
