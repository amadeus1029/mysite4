package com.javaex.dao;


import com.javaex.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private SqlSession sqlSession;

    public List<BoardVo> getList() {
        return sqlSession.selectList("board.getList");
    }

    public BoardVo getBoard(int boardNo) {
        sqlSession.update("board.updateView",boardNo);
        return sqlSession.selectOne("board.getBoard",boardNo);
    }

    public void write(BoardVo boardVo) {
        sqlSession.insert("board.insert",boardVo);
    }

    public void modify(BoardVo boardVo) {
        System.out.println(boardVo.toString());
        sqlSession.update("board.update",boardVo);
    }

    public void delete(int boardNo) {
        sqlSession.delete("board.delete",boardNo);
    }
}
