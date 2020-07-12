package com.javaex.service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public List<BoardVo> getList(SearchVo searchVo) {
        return boardDao.getList(searchVo);
    }

    public int getCount(SearchVo searchVo) {
        return boardDao.getCount(searchVo);
    }

    public BoardVo getBoard(int boardNo) {
        boardDao.increaseHit(boardNo);
        return boardDao.getBoard(boardNo);
    }

    public void write(BoardVo boardVo) {
        boardDao.write(boardVo);
    }

    public void modify(BoardVo boardVo) {
        boardDao.modify(boardVo);
    }

    public void delete(int boardNo) {
        boardDao.delete(boardNo);
    }
}
