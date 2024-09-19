package com.teamx.retroStore.dto.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Page<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 2780804876406919746L;

    private long totalNoOfRecords = 0;

    private int startPosition = 0;

    private int noOfRecords = 0;

    private int pageLength = 0;

    private Collection<T> page = new ArrayList<T>();

    public Page() {
    }

    public Page(long totalNoOfRecords, int startPosition, int noOfRecords, Collection<T> page) {
        this.totalNoOfRecords = totalNoOfRecords;
        this.startPosition = startPosition;
        this.noOfRecords = noOfRecords;
        this.page = page;
    }

    public Page(long totalNoOfRecords, int startPosition, int noOfRecords, int pageLength, Collection<T> page) {
        this.totalNoOfRecords = totalNoOfRecords;
        this.startPosition = startPosition;
        this.noOfRecords = noOfRecords;
        this.pageLength = pageLength;
        this.page = page;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int endPosition) {
        this.noOfRecords = endPosition;
    }

    public Collection<T> getPageData() {
        return page;
    }

    public void setPageData(Collection<T> pageData) {
        this.page = pageData;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public long getTotalNoOfRecords() {
        return totalNoOfRecords;
    }

    public void setTotalNoOfRecords(long totalNoOfRecords) {
        this.totalNoOfRecords = totalNoOfRecords;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public int getTotalNoOfPages() {
        int totalNoOfPages = 1;
        if (pageLength != 0) {
            totalNoOfPages = (int) totalNoOfRecords / pageLength;
            if (totalNoOfRecords % pageLength > 0)
                totalNoOfPages++;
        }
        return totalNoOfPages;
    }

    public int getCurrentPageNo() {
        int currentPageNo = 1;
        if (pageLength != 0) {
            currentPageNo = startPosition / pageLength + 1;
        }
        return currentPageNo;
    }
}
