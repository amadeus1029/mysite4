package com.javaex.vo;

public class SearchVo {
    private String category, keyword;
    private int page;

    public SearchVo() {
    }

    public SearchVo(String category, String keyword, int page) {
        this.category = category;
        this.keyword = keyword;
        this.page = page;
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

    @Override
    public String toString() {
        return "SearchVo{" +
                "category='" + category + '\'' +
                ", keyword='" + keyword + '\'' +
                ", page=" + page +
                '}';
    }
}
