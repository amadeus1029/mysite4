package com.javaex.vo;

public class ReplyBoardVo {
    private int replyBoardNo, userNo, hit, groupNo, orderNo, depth, deleted;
    private String title, content, regDate;

    public ReplyBoardVo() {
    }

    public ReplyBoardVo(int replyBoardNo, int userNo, int hit, int groupNo, int orderNo, int depth, int deleted, String title, String content, String regDate) {
        this.replyBoardNo = replyBoardNo;
        this.userNo = userNo;
        this.hit = hit;
        this.groupNo = groupNo;
        this.orderNo = orderNo;
        this.depth = depth;
        this.deleted = deleted;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
    }

    public int getReplyBoardNo() {
        return replyBoardNo;
    }

    public void setReplyBoardNo(int replyBoardNo) {
        this.replyBoardNo = replyBoardNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "ReplyBoardVo{" +
                "replyBoardNo=" + replyBoardNo +
                ", userNo=" + userNo +
                ", hit=" + hit +
                ", groupNo=" + groupNo +
                ", orderNo=" + orderNo +
                ", depth=" + depth +
                ", deleted=" + deleted +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
