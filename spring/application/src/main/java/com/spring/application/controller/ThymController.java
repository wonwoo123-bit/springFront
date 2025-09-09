package com.spring.application.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.application.dto.NoticeVO;


@Controller
@RequestMapping("/thyme")
public class ThymController {
    @GetMapping("/test")
    public String getMethodName(Model model) {
        String url = "thyme/test";

        NoticeVO notice = new NoticeVO();
        notice.setContent("내용");
        notice.setNno(1234);
        notice.setRegDate(new Date());
        notice.setTitle("제목");
        notice.setViewcnt(11);
        notice.setWriter("작성자");

        model.addAttribute("notice", notice);
        model.addAttribute("str", "abcdefg" );

        return url;
    }
    
}
