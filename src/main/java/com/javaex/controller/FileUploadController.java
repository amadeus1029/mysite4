package com.javaex.controller;

import com.javaex.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/fileupload")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value="/form")
    public String form() {
        return "fileupload/form";
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {

        String saveName = fileUploadService.restore(file);
        model.addAttribute("saveName", saveName);


        return "fileupload/result";
    }
}


