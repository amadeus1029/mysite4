package com.javaex.controller;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

    @Autowired
    private GuestbookService guestbookService;

    @RequestMapping(value = "/list") /* 이렇게 축약도 가능하다 */
    public String list(Model model) {
        List<GuestbookVo> gbList = guestbookService.getList();
        model.addAttribute("gbList",gbList);
        return "guestbook/addList";
    }

    @RequestMapping(value = "/write")
    public String write(@ModelAttribute GuestbookVo guestbookVo) {
        guestbookService.write(guestbookVo);
        return "redirect:/guestbook/list";
    }

    @RequestMapping(value= "/deleteForm")
    public String deleteForm() {
        return "guestbook/deleteForm";
    }

    @RequestMapping(value="/delete")
    public String delete(@ModelAttribute GuestbookVo guestbookVo) {
         guestbookService.delete(guestbookVo);
        return "redirect:/guestbook/list";
    }

}
