package com.javaex.controller;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/joinForm") /* 이렇게 축약도 가능하다 */
    public String joinForm() {
        return "user/joinForm";
    }

    @RequestMapping(value = "/join")
    public String join(@ModelAttribute UserVo userVo) {
        System.out.println("userservice:join");
        userService.join(userVo);
        return "user/joinOk";
    }

    @RequestMapping(value = "/loginForm")
	public String loginForm() {
    	return "user/loginForm";
	}

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute UserVo userVo,HttpSession session) {
		UserVo authUser = userService.login(userVo);
        if(authUser != null) { //로그인 성공
            session.setAttribute("authUser",authUser);
            System.out.println("로그인성공");
            return "redirect:/main";
        } else { //로그인 실패
            System.out.println("로그인실패");
            return "redirect:/user/loginForm?loginResult=fail";
        }
	}
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("authUser");
        session.invalidate();

        return "redirect:/main";
    }

    @RequestMapping(value = "/modifyForm")
    public String modifyForm(Model model,HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        UserVo userVo = userService.getInfo(authUser);
        model.addAttribute("userVo",userVo);
        return "user/modifyForm";
    }

    @RequestMapping(value = "/modify")
    public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
        UserVo authUser = (UserVo)session.getAttribute("authUser");
        userVo.setNo(authUser.getNo());
        userService.modify(userVo);
        authUser.setName(userVo.getName());
        return "redirect:/main";
    }
    /*
    AJAX 관련코드 시작
    */

    //아이디 중복체크
    @ResponseBody
    @RequestMapping(value = "/idcheck")
    public boolean idcheck(@RequestParam("userId") String userId) {
        UserVo userVo = userService.checkId(userId);

        if(userVo == null) {
            return false;
        } else {
            return true;
        }
    }
}
