package io.ymq.example.mongodb;

import java.util.List;

/**
 * Created by 86133 on 2020/8/24.
 */
public class Page<T> {

    private int pagenumber;
    private long total;
    private int pageSize;
    private List<T> rows;


    public int getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
