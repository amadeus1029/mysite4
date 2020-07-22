package com.javaex.controller;


import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<GalleryVo> galleryList = galleryService.getList();
        model.addAttribute("galleryList",galleryList);
        return "gallery/list";
    }

    @RequestMapping("/add")
    public String add(@RequestParam("file") MultipartFile file,
                      @RequestParam("content") String content,
                      @ModelAttribute GalleryVo galleryVo,
                      HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");

        galleryVo.setUserNo(authUser.getNo());
        galleryVo.setContent(content);

        galleryService.add(file, galleryVo);

        return "redirect:list";
    }

    @ResponseBody
    @RequestMapping(value ="/view")
    public GalleryVo view(@RequestBody GalleryVo galleryVo) {
        return galleryService.getView(galleryVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public Boolean delete(@RequestBody GalleryVo galleryVo,
                          HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");

        galleryVo.setUserNo(authUser.getNo());
        return galleryService.delete(galleryVo);
    }
}
