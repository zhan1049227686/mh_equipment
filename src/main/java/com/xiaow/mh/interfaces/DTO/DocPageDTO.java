package com.xiaow.mh.interfaces.DTO;


import java.util.List;

/**
 * Created by zhangnengwen on 17/11/16.
 */
public class DocPageDTO<T>{

    private Integer page;

    private Integer size;

    private Integer totalPage;

    private Integer totalCount;

    private List<T> tList;

    public DocPageDTO(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public DocPageDTO() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {

        this.totalCount = totalCount;

        this.setTotalPage(totalCount % size > 0 ? (totalCount / size) + 1 : totalCount / size);
    }

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
    }
}
