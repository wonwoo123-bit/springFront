package com.spring.application.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.application.command.NoticeModifyCommand;
import com.spring.application.command.NoticeRegistCommand;
import com.spring.application.command.PageMaker;
import com.spring.application.dto.NoticeVO;
import com.spring.application.service.NoticeService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;


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
    public String detail(int nno, Model model, HttpServletRequest request) throws Exception{
        String url = "/notice/detail";

        ServletContext application = request.getServletContext();

        String key = "notice:"+nno;
        Object value = application.getAttribute(key);

        NoticeVO notice = null;
        if(value!=null){
            notice = noticeService.getNotice(nno);
        }else{
            notice = noticeService.detail(nno);
            application.setAttribute(key, "");
        }

        model.addAttribute("notice", notice);
        return url;
    }

    @GetMapping("/modify")
    public void modifyForm(int nno, Model model) throws Exception{
        NoticeVO notice = noticeService.getNotice(nno);

        model.addAttribute("notice", notice);
    }

    @PostMapping("/modify")
    public String modifyPost(@ModelAttribute("command") NoticeModifyCommand command, Model model) throws Exception{
        String url = "/notice/modify_success";

        NoticeVO notice = command.toNoticeVO();
        noticeService.modify(notice);

        return url;
    
    }
    @GetMapping("/remove")
    public  String remove(int nno, RedirectAttributes rttr) throws Exception{
        String url = "/notice/remove_success";

        noticeService.remove(nno);
        
        return url;
    }

}
