package com.jeecms.cms.rest2.common;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Page {
    private Integer start = 0;
    private Integer end = 15;
    private Integer pageNum = 1;// 页数从1开始计数;
    private Integer sizeNum = 15;

    private String trim(Object obj) {
        return obj != null ? obj.toString().trim() : null;
    }

    public Page(Map<String, Object> map) {
        String page = trim(map.get("PAGENO"));
        String size = trim(map.get("PAGESIZE"));
        if (StringUtils.isNumeric(size)) {
            this.sizeNum = Integer.parseInt(size);
        }
        if (StringUtils.isNumeric(page)) {
            this.pageNum = Integer.parseInt(page);
            this.start = (this.pageNum - 1) * this.sizeNum;
            this.end = this.pageNum * this.sizeNum;
        }
    }

    public Page(String page, String size) {
        if (StringUtils.isNumeric(size)) {
            this.sizeNum = Integer.parseInt(size);
        }
        if (StringUtils.isNumeric(page)) {
            this.pageNum = Integer.parseInt(page);
            this.start = (this.pageNum - 1) * this.sizeNum;
            this.end = this.pageNum * this.sizeNum;
        }
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getSizeNum() {
        return sizeNum;
    }

    public void setSizeNum(Integer sizeNum) {
        this.sizeNum = sizeNum;
    }

}
