package cn.sigo.sigocloudprovider.elastic.model;

import java.io.Serializable;

/**
 * @Auther: lxj
 * @Date: 2019/8/26 14:17
 * @Description:
 */
public class PageInfo implements Serializable {
    private Integer pageSize;

    private Integer pageIndex;

    private Long total;

    private Long pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
