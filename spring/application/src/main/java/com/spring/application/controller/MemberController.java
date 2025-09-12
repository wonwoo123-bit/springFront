package com.spring.application.controller;



import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.application.command.MemberRegistCommand;
import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;
import com.spring.application.dto.NoticeVO;
import com.spring.application.service.MemberService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/member")
public record MemberController(MemberService memberService) {

    @GetMapping("/list")
    public void list(@ModelAttribute(name="pageMaker") PageMaker pageMaker, Model model)throws Exception{        
        List<MemberVO> memberList = memberService.list(pageMaker);
        
        model.addAttribute("memberList", memberList);
    }

    @GetMapping("/registForm")
    public String registForm(){
        String url = "/member/regist";
        return url;
    }
    @PostMapping("/regist")
    public String regist(MemberRegistCommand command) throws Exception{
        String url = "/member/regist_success";

        memberService.regist(command());

        return url;
    }
    
    
}
