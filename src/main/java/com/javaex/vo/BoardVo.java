package com.javaex.vo;

public class BoardVo {
    private int boardNo, writerNo, views;
    private String title,writer, regDate, content;

    public BoardVo() {
    }

    public BoardVo(int boardNo, int writerNo, int views, String title, String writer, String regDate, String content) {
        this.boardNo = boardNo;
        this.writerNo = writerNo;
        this.views = views;
        this.title = title;
        this.writer = writer;
        this.regDate = regDate;
        this.content = content;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getWriterNo() {
        return writerNo;
    }

    public void setWriterNo(int writerNo) {
        this.writerNo = writerNo;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BoardVo{" +
                "boardNo=" + boardNo +
                ", writerNo=" + writerNo +
                ", views=" + views +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate='" + regDate + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
