package com.javaex.dao;

import com.javaex.vo.ReplyBoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReplyBoardDao {

    @Autowired
    private SqlSession sqlSession;

    public List<ReplyBoardVo> getList() {
        return sqlSession.selectList("replyboard.getList");
    }

    public ReplyBoardVo getBoard(int replayBoardNo) {
        return sqlSession.selectOne("replyboard.getone",replayBoardNo);
    }
}
