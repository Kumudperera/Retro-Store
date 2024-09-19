package com.teamx.retroStore.dto.common;

import java.io.Serializable;


public class PagedParamDTO implements Serializable {

    private Integer page;

    private Integer rows;

    public Integer getPage() {
        if (page == null) {
            page = 1;
        }
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        if (rows == null) {
            rows = 10;
        }
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
