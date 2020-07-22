package com.javaex.vo;

public class SearchVo {
    private String category, keyword;
    private int page, pageView;

    public SearchVo() {
    }

    public SearchVo(String category, String keyword, int page, int pageView) {
        this.category = category;
        this.keyword = keyword;
        this.page = page;
        this.pageView = pageView;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageView() {
        return pageView;
    }

    public void setPageView(int pageView) {
        this.pageView = pageView;
    }

    @Override
    public String toString() {
        return "SearchVo{" +
                "category='" + category + '\'' +
                ", keyword='" + keyword + '\'' +
                ", page=" + page +
                ", pageView=" + pageView +
                '}';
    }
}
