package com.javaex.dao;

import com.javaex.vo.GuestbookVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestbookDao {

    @Autowired
    private SqlSession sqlSession;

    public List<GuestbookVo> getList() {
        return sqlSession.selectList("guestbook.getList");
    }

    public int insert(GuestbookVo guestbookVo) {
        return sqlSession.insert("guestbook.insert",guestbookVo);
    }

    public int delete(GuestbookVo guestbookVo) {
        return sqlSession.delete("guestbook.delete",guestbookVo);
    }

}
