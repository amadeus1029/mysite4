package com.javaex.service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public List<BoardVo> getList() {
        return boardDao.getList();
    }

    public BoardVo getBoard(int boardNo) {
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
