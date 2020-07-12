package com.javaex.controller;


import com.javaex.service.ReplyBoardService;
import com.javaex.vo.ReplyBoardVo;
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
@RequestMapping("/replyBoard")
public class ReplyBoardController {

    @Autowired
    private ReplyBoardService replyBoardService;

    @RequestMapping(value = "/list")
    public String list(Model model, @ModelAttribute SearchVo searchVo, @RequestParam(value = "page", defaultValue = "1") int page) {

        List<ReplyBoardVo> replyBoardList;
        searchVo.setPage(page);
        replyBoardList = replyBoardService.getList(searchVo);
        int totalCount = replyBoardService.getCount(searchVo);
        model.addAttribute("page",page);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("replyBoardList",replyBoardList);
        return "replyBoard/list";
    }

    @RequestMapping(value = "/read")
    public String read(Model model, @RequestParam("replyBoardNo") int replyBoardNo) {
        ReplyBoardVo replyBoardVo = replyBoardService.getBoard(replyBoardNo);
        model.addAttribute("replyBoardVo",replyBoardVo);
        return "replyBoard/read";
    }

    @RequestMapping(value = "/writeForm")
    public String writeForm() {
        return "replyBoard/writeForm";
    }

    @RequestMapping(value ="/write")
    public String write(@ModelAttribute ReplyBoardVo replyBoardVo, HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        replyBoardVo.setUserNo(authUser.getNo());

        replyBoardService.write(replyBoardVo);
        int goTo = replyBoardVo.getReplyBoardNo();

        return "redirect:read?replyBoardNo="+goTo;
    }

    @RequestMapping(value = "/modifyForm")
    public String modifyForm(Model model, @RequestParam("replyBoardNo") int replyBoardNo) {
        ReplyBoardVo replyBoardVo = replyBoardService.getBoard(replyBoardNo);
        model.addAttribute(replyBoardVo);
        return "replyBoard/modifyForm";
    }

    @RequestMapping(value = "/modify")
    public String modify(@ModelAttribute ReplyBoardVo replyBoardVo, HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(replyBoardVo.getUserNo() == authUser.getNo()) { //url 변조 방지
            replyBoardService.modify(replyBoardVo);
        }
        return "redirect:read?replyBoardNo="+replyBoardVo.getReplyBoardNo();
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam("replyBoardNo") int replyBoardNo, @RequestParam("userNo") int userNo, HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        if(userNo == authUser.getNo()) { //url 변조 방지
            replyBoardService.delete(replyBoardNo);
        }
        return "redirct:list";
    }

}
