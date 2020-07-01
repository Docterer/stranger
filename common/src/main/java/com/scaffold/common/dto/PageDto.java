package com.scaffold.common.dto;

import java.io.Serializable;

/**
 * @Author danyiran
 * @create 2020/7/1 23:29
 */
public class PageDto implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    public PageDto() {
    }

    public PageDto(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDto{");
        sb.append("pageNo=").append(pageNo);
        sb.append(", pageSize=").append(pageSize);
        sb.append('}');
        return sb.toString();
    }

}
