package com.spring.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.application.command.PageMaker;
import com.spring.application.command.PdsModifyCommand;
import com.spring.application.command.PdsRegistCommand;
import com.spring.application.dto.MemberVO;
import com.spring.application.dto.PdsVO;
import com.spring.application.service.PdsService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.val;



@Controller
@RequestMapping("/pds")
public class PdsController {
    @Autowired
    private PdsService pdsService;

    @GetMapping("/main")
    public void main(){}
    
    @GetMapping("/list")
    public String list(@ModelAttribute(name = "pageMaker")PageMaker pageMaker, Model model)throws Exception{
        String url = "pds/list";

        model.addAttribute("pdsList",pdsService.list(pageMaker));

        return url;
    }

    @GetMapping("detail")
    public String detail(int pno, Model model, HttpServletRequest request)throws Exception{
        String url = "/pds/detail";
        //로그인 가정으로 만드는거임

        //요청한 주소 가져오기
        ServletContext app = request.getServletContext();
        //로그인한 세션에서 로그인한 정보 가져오기
        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");

        String key = "pds:"+loginUser.getId()+pno;
        Object value = session.getAttribute(key);

        PdsVO pds = null;
        if (value != null) {
            pds=pdsService.getPds(pno);
        }else{
            pds=pdsService.detail(pno);
            app.setAttribute(key, "");
        }
        model.addAttribute("pds", pds);


        return url;
    }

    @GetMapping("/registForm")
    //화면 보여주는 용도임 url만 입려해두고 처리는 postmapping으로
    public String registForm()throws Exception{
        String url = "/pds/regist";
        return url;
    }

    @PostMapping("/regist")
    public String regist(PdsRegistCommand cmd)throws Exception{
        String url = "/pds/regist_success";
        pdsService.regist(cmd.toPdsVO());
        return url;
    }

    @GetMapping("/modify")
    public void modifyForm(int pno, Model model)throws Exception{
        PdsVO pds = pdsService.getPds(pno);
        
    }

    @PostMapping("/modify")
    public String modify(PdsModifyCommand mcmd, int pno)throws Exception{
        String url = "/pds/modify";
        pdsService.modify(mcmd.toPdsVO());

        return url;
        
    }

    @PostMapping("/delete")
    public String delete(int pno)throws Exception{
        String url = "/pds/delete";
        return url;
    }
}
