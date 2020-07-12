package com.javaex.dao;

import com.javaex.vo.BoardVo;
import com.javaex.vo.ReplyBoardVo;
import com.javaex.vo.SearchVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyBoardDao {

    @Autowired
    private SqlSession sqlSession;

    //게시글 리스트 뽑아주기
    public List<ReplyBoardVo> getList(SearchVo searchVo) {
        return sqlSession.selectList("replyBoard.getList",searchVo);
    }

    //게시물 갯수 뽑아주기
    public int getCount(SearchVo searchVo) {
        return sqlSession.selectOne("replyBoard.getCount",searchVo);
    }

    //게시글 읽기
    public ReplyBoardVo getBoard(int replyBoardNo) {
        return sqlSession.selectOne("replyBoard.getBoard",replyBoardNo);
    }

    //게시글 조회수 증가
    public void increaseHit(int replyBoardNo) {
        sqlSession.update("replyBoard.increaseHit",replyBoardNo); //조회수 1 추가
    }

    //게시글 작성
    public void write(ReplyBoardVo replyBoardVo) {
        sqlSession.insert("replyBoard.insert",replyBoardVo);
    }

    //게시글 수정
    public void modify(ReplyBoardVo replyBoardVo) {
        sqlSession.update("replyBoard.update",replyBoardVo);
    }

    //게시글 삭제
    public void delete(int replyBoardNo) {
        sqlSession.update("replyBoard.delete",replyBoardNo);
    }
}
