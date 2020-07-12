package com.javaex.service;


import com.javaex.dao.ReplyBoardDao;
import com.javaex.vo.ReplyBoardVo;
import com.javaex.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyBoardService {
    @Autowired
    private ReplyBoardDao replyBoardDao;

    public List<ReplyBoardVo> getList(SearchVo searchVo) {
        return replyBoardDao.getList(searchVo);
    }

    public int getCount(SearchVo searchVo) {
        return replyBoardDao.getCount(searchVo);
    }

    public ReplyBoardVo getBoard(int replyBoardNo) {
        replyBoardDao.increaseHit(replyBoardNo);
        return replyBoardDao.getBoard(replyBoardNo);
    }

    public void write(ReplyBoardVo replyBoardVo) {
        replyBoardDao.write(replyBoardVo);
    }

    public void modify(ReplyBoardVo replyBoardVo) {
        replyBoardDao.modify(replyBoardVo);
    }

    public void delete(int replyBoardNo) {
        replyBoardDao.delete(replyBoardNo);
    }
}
