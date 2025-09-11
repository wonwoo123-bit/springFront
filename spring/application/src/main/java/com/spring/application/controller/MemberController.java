package com.spring.application.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;
import com.spring.application.service.MemberService;

@Controller
@RequestMapping("/member")
public record MemberController(MemberService memberService) {

    @GetMapping("/list")
    public void list(@ModelAttribute(name="pageMaker") PageMaker pageMaker, Model model)throws Exception{        
        List<MemberVO> memberList = memberService.list(pageMaker);
        model.addAttribute("memberList", memberList);
    }
    
    
}
