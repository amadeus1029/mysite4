package com.javaex.dao;


import com.javaex.vo.BoardVo;
import com.javaex.vo.SearchVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private SqlSession sqlSession;

    //게시글 리스트 뽑아주기
    public List<BoardVo> getList() {
        return sqlSession.selectList("board.getList");
    }

    //게ㅅ글 검색
    public List<BoardVo> getList(SearchVo searchVo) {
        return sqlSession.selectList("board.searchList",searchVo);
    }

    //게시글 읽기
    public BoardVo getBoard(int boardNo) {
        return sqlSession.selectOne("board.getBoard",boardNo);
    }

    //게시글 조회수 증가
    public void increaseHit(int boardNo) {
        sqlSession.update("board.increaseHit",boardNo); //조회수 1 추가
    }

    //게시글 작성
    public void write(BoardVo boardVo) {
        sqlSession.insert("board.insert",boardVo);
    }

    //게시글 수정
    public void modify(BoardVo boardVo) {
        System.out.println(boardVo.toString());
        sqlSession.update("board.update",boardVo);
    }

    //게시글 삭제
    public void delete(int boardNo) {
        sqlSession.delete("board.delete",boardNo);
    }
}
