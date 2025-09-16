package com.spring.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.application.dto.MemberVO;
import com.spring.application.exeption.InvalidPasswordException;
import com.spring.application.exeption.NotFoundIDException;
import com.spring.application.service.MemberService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private MemberService memberService;
    
    @GetMapping("loginForm")
    public void getMethodName() {}
    
    @PostMapping("/login")
    public String login(String id, String pwd, HttpSession session, RedirectAttributes rttr){
        String url = "redirect:/index.html";

        try{
            MemberVO loginUser = memberService.login(id, pwd);
            session.setAttribute("loginUser", loginUser);
        }catch(NotFoundIDException | InvalidPasswordException e){
            url = "redirect:/common/loginForm";
            rttr.addFlashAttribute("msg",e.getMessage());
        }catch(Exception e){
            url = "/error/500";
        }
        return url;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        String url = "redirect:/";
        session.invalidate();
        return url;
    }
    
}
