package com.javaex.controller;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.SearchVo;
import com.javaex.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/list")
    public String list(Model model, @ModelAttribute SearchVo searchVo, @RequestParam(value = "page", defaultValue = "1") int page) {

        List<BoardVo> boardList;
        searchVo.setPage(page);
        boardList = boardService.getList(searchVo);
        int totalCount = boardService.getCount(searchVo);
        model.addAttribute("page",page);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("boardList",boardList);
        return "board/list";
    }

    @RequestMapping(value = "/read")
    public String read(Model model, @RequestParam("boardNo") int boardNo) {
        BoardVo boardVo = boardService.getBoard(boardNo);
        model.addAttribute("boardVo", boardVo);
        return "board/read";
    }

    @RequestMapping(value = "/writeForm")
    public String writeForm() {
        return "board/writeForm";
    }

    @RequestMapping(value = "/write")
    public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        boardVo.setWriterNo(authUser.getNo());

        boardService.write(boardVo);
        int goTo = boardVo.getBoardNo();

        return "redirect:read?boardNo="+goTo;
    }

    @RequestMapping(value = "/modifyForm")
    public String modifyForm(Model model,@RequestParam("boardNo") int boardNo) {
        BoardVo boardVo = boardService.getBoard(boardNo);
        model.addAttribute(boardVo);
        return "board/modifyForm";
    }

    @RequestMapping(value ="/modify")
    public String modify(@ModelAttribute BoardVo boardVo,HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(boardVo.getWriterNo() == authUser.getNo()) { //url 변조 방지
            boardService.modify(boardVo);
        }
        return "redirect:read?boardNo="+boardVo.getBoardNo();
    }

    @RequestMapping(value ="/delete")
    public String delete(@RequestParam("boardNo") int boardNo,@RequestParam("writerNo") int writerNo, HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(writerNo == authUser.getNo()) { //url 변조 방지
            boardService.delete(boardNo);
        }
        return "redirect:list";
    }

}
