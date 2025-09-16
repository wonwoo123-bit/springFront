package com.spring.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.application.dto.MemberVO;
import com.spring.application.exeption.InvalidPasswordException;
import com.spring.application.exeption.NotFoundIDException;
import com.spring.application.service.MemberService;

import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private MemberService memberService;
    
    @GetMapping("loginForm")
    public void getMethodName(String retUrl, Model model){
        model.addAttribute("retUrl", retUrl);
    }
    
    @PostMapping("/login")
    public String login(String id, String pwd,String retUrl, HttpSession session, RedirectAttributes rttr){
        String url = "redirect:/index.html";


        //로그인 성공, 실패이후 동작 로직
        try{
            MemberVO loginUser = memberService.login(id, pwd);
            session.setAttribute("loginUser", loginUser);
            session.setMaxInactiveInterval(6*60); //로그인 유지시간 주는 방법
            //로그인 성공하면 원래 요청한 url 리턴값으로 받아서 다시 호출
            if(retUrl != null && !retUrl.isEmpty()){
                url = "redirect:"+retUrl;
            }
        }catch(NotFoundIDException | InvalidPasswordException e){
            url = "redirect:/common/loginForm";
            rttr.addFlashAttribute("msg",e.getMessage());
            //로그인 실패 이후 다시 로그인 시도해도 returl을 돌려받을 수 있도록 해주는 조건
            if (retUrl!=null && !retUrl.isEmpty()) {
                rttr.addAttribute("retUrl",retUrl);
            }
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
