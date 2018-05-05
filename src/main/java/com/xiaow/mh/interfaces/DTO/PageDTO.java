package com.xiaow.mh.interfaces.DTO;

import java.util.List;

/**
 * Created by zhangnengwen on 16/12/23.
 */
public class PageDTO<T> {

    private Integer page;

    private Integer size;

    private Integer totalPage;

    private Integer totalCount;

    private List<T> tList;

    public PageDTO(Integer page, Integer size, List<T> tList) {
        this.page = page;
        this.size = size;
        this.totalPage = tList.size() / size +1;
        this.totalCount = tList.size();

        if(tList.size() < page * size +1){
            this.tList = null;
        }

        if(tList.size() >= page * size +1 && tList.size() < (page + 1) * size){
            this.tList = tList.subList(page * size, tList.size());
        }

        if(tList.size() >= (page + 1) * size){
            this.tList = tList.subList(page * size , (page + 1) * size );
        }
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

    public List<T> gettList() {
        return tList;
    }

    public void settList(List<T> tList) {
        this.tList = tList;
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
    }
}
