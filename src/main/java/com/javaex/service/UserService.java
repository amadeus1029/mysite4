package com.javaex.service;

import com.javaex.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.vo.UserVo;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int join(UserVo userVo) {
        return userDao.insert(userVo);
    }

    public UserVo login(UserVo userVo) {
        return userDao.selectUser(userVo);
    }

    public UserVo getInfo(UserVo userVo) {
        return userDao.getInfo(userVo);
    }

    public void modify(UserVo userVo) {
        userDao.update(userVo);
    }

}
