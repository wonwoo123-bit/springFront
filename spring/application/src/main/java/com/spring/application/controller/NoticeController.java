package com.spring.application.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.application.command.NoticeRegistCommand;
import com.spring.application.command.PageMaker;
import com.spring.application.dto.NoticeVO;
import com.spring.application.service.NoticeService;


@Controller
@RequestMapping("/notice")
public record NoticeController(NoticeService noticeService) {

    @GetMapping("/list")
    public String list(@ModelAttribute(name="pageMaker") PageMaker pageMaker, Model model) throws Exception{
    String url = "/notice/list";

        List<NoticeVO> noticelList = noticeService.list(pageMaker);

        model.addAttribute("noticeList",noticelList);

        return url;
    }
    @GetMapping("/registForm")
    public String registForm()throws Exception{
        String url="/notice/regist";
        return url;
    }
    @PostMapping("/regist")
    public String regist(NoticeRegistCommand command) throws Exception{
        String url = "/notice/regist_success";

        noticeService.regist(command.toNoticeVO());

        return url;
    }
    @GetMapping("/detail")
    public String detail(int nno, Model model) throws Exception{
        String url = "/notice/detail";
        model.addAttribute("notice", noticeService.detail(nno));
        return url;
    }

}
