package com.javaex.vo;

public class SearchVo {
    private String category, keyword;

    public SearchVo() {
    }

    public SearchVo(String category, String keyword) {
        this.category = category;
        this.keyword = keyword;
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

    @Override
    public String toString() {
        return "SearchVo{" +
                "category='" + category + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
