package com.javaex.vo;

public class PageVo {
    private int currPage, beginPage, endPage, totalPage;

    public PageVo() {
    }

    public PageVo(int currPage, int beginPage, int endPage, int totalPage) {
        this.currPage = currPage;
        this.beginPage = beginPage;
        this.endPage = endPage;
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "currPage=" + currPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", totalPage=" + totalPage +
                '}';
    }
}
