package com.javaex.api.controller;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @RequestMapping(value = "/write", method = RequestMethod.POST) //이건 걍 싸본거 + json데이터 받아보기
    public GuestbookVo write(@RequestBody GuestbookVo guestbookVo) {
        return guestbookService.addGuest(guestbookVo);
    }

    @ResponseBody
    @RequestMapping(value ="/delete")
    public int delete(@ModelAttribute GuestbookVo guestbookVo) {
        return guestbookService.delete(guestbookVo);
    }


}
