package com.spring.boot.bean;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询参数
 * Created by TonyZeng on 2017/11/13.
 */
public class PageInfoBean<T> implements Serializable {
    public PageInfoBean() {
    }

    public PageInfoBean(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。https://blog.csdn.net/weixin_42080277/article/details/84136240
     * @param list          page结果
     * @param
     */
    public PageInfoBean(List list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.setPageNum(page.getPageNum());
            this.setPageSize(page.getPageSize());
            this.setTotalOfPage(page.getPages());
            this.setList(page);
            this.setTotalOfData(page.getTotal());
        } else {
            this.setPageNum(1);
            this.setPageSize(list.size());
            this.setTotalOfPage(this.getPageSize() > 0 ? 1 : 0);
            this.setList(list);
            this.setTotalOfData((long) list.size());
        }
    }

    public PageInfoBean(Integer pageNum, Integer pageSize, String sortKey, Integer sortRule) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sortKey = sortKey;
        this.sortRule = sortRule;
    }

    public PageInfoBean(Integer pageNum, Integer pageSize, String sortKey, Integer sortRule, Integer totalOfPage, Long totalOfData) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sortKey = sortKey;
        this.sortRule = sortRule;
        this.totalOfPage = totalOfPage;
        this.totalOfData = totalOfData;
    }

    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 一页数据条数
     */
    private Integer pageSize;
    /**
     * 排序字段
     */
    private String sortKey;
    /**
     * 排序规则(0=asc 1=desc)
     */
    private Integer sortRule;
    /**
     * 数据
     */
    private List<T> list;
    /**
     * 总页数
     */
    private Integer totalOfPage;
    /**
     * 数据总数
     */
    private Long totalOfData;

    public Integer getPageNum() {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        //每页最多显示50条数据，防止漏洞被利用，导致服务崩溃
        if (pageSize == null || pageSize <= 0 || pageSize >= 100) {
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public Integer getSortRule() {
        if (this.sortRule == null) {
            return 1;
        }
        return sortRule;
    }

    public void setSortRule(Integer sortRule) {
        this.sortRule = sortRule;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalOfPage() {
        return totalOfPage;
    }

    public void setTotalOfPage(Integer totalOfPage) {
        this.totalOfPage = totalOfPage;
    }

    public Long getTotalOfData() {
        return totalOfData;
    }

    public void setTotalOfData(Long totalOfData) {
        this.totalOfData = totalOfData;
    }

    @Override
    public String toString() {
        return "PageInfoBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", sortKey='" + sortKey + '\'' +
                ", sortRule=" + sortRule +
                ", list=" + list +
                ", totalOfPage=" + totalOfPage +
                ", totalOfData=" + totalOfData +
                '}';
    }
}