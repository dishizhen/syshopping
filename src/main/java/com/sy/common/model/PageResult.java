package com.sy.common.model;

public class PageResult {

    /**
     * 每页条数
     */

    private Integer limit;

    /**
     * 总记录数
     */
    private Integer count;

    //总页数
    private int totalPage ;

    /**
     * 当前页
     */
    private Integer page;


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
