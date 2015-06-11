package com.zl.pojo;

import java.io.Serializable;
import java.util.Date;

public class ChatQuery implements Serializable {

    /**  */
    private static final long serialVersionUID = -8199178486449651952L;

    private Long              userId;

    private int               offset;

    private int               limit;

    private Date              since;

    private Date              max;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Date getMax() {
        return max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

}
