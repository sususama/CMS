package edu.xalead.cms.vo;

import java.util.List;

public class pageVo<T> {
    //当前页起始索引
    private int offset;
    //每页记录数
    private int pagesize;
    //总记录数
    private int count;
    //当前页的结果集列表
    private List<T> datas = null;

    public int getFirst(){
        return 0;
    }

    public int getLast(){
        return count % pagesize == 0 ? count - pagesize : count - (count % pagesize);
    }
    public int getCurrentPage(){
        return offset / pagesize;
    }

    public int getPageCount(){
        return count % pagesize == 0 ? count / pagesize : count / pagesize + 1;
    }
    public int getPrevious(){
        return offset - pagesize < 0 ? 0 : offset - pagesize;
    }

    public int getNext(){
        return offset + pagesize > count ? count : offset + pagesize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
