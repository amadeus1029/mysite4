package com.javaex.dao;

import com.javaex.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private SqlSession sqlSession;

    public int insert(UserVo userVo) {
        return sqlSession.insert("user.insert",userVo);
    }

    public UserVo selectUser(UserVo userVo) {
        return sqlSession.selectOne("user.selectUser",userVo);
    }

    public UserVo getInfo(UserVo userVo) {
        return sqlSession.selectOne("user.getInfo",userVo);
    }

    public int update(UserVo userVo) {
        return sqlSession.update("user.update",userVo);
    }

    public UserVo selectUser(String userId) {
        return sqlSession.selectOne("user.selectById",userId);
    }
}
