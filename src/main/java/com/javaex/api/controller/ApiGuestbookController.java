package com.javaex.api.controller;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {

    @Autowired
    private GuestbookService guestbookService;

    @ResponseBody
    @RequestMapping(value="/list")
    public List<GuestbookVo> list() {
        List<GuestbookVo> gbList = guestbookService.getList();
        return gbList;
    }

}
