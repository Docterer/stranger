package com.scaffold.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Author danyiran
 * @create 2020/7/1 23:30
 */
public class PageResult<T> implements Serializable {

    private long total;

    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageResult{");
        sb.append("total=").append(total);
        sb.append(", rows=").append(rows);
        sb.append('}');
        return sb.toString();
    }
}
