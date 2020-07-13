package com.javaex.service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.PageVo;
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

    public PageVo getPageInfo(SearchVo searchVo) {
        int pageView = 5; //한 페이지에 표시할 게시물 수
        int pageNum = 10; //화면 하단에 표시할 페이지 최대 갯수
        int currPage = searchVo.getPage();
        int totalPage = (boardDao.getCount(searchVo)-1)/pageView + 1;
        int _currPage = (currPage-1)/pageNum;
        int beginPage = _currPage*pageNum+1;
        int endPage = _currPage*pageNum+pageNum;

        if(endPage >= totalPage) {
            endPage = totalPage;
        }

        PageVo pageVo = new PageVo();
        pageVo.setPageNum(pageNum);
        pageVo.setPageView(pageView);
        pageVo.setCurrPage(currPage);
        pageVo.setTotalPage(totalPage);
        pageVo.setBeginPage(beginPage);
        pageVo.setEndPage(endPage);
        return pageVo;
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
