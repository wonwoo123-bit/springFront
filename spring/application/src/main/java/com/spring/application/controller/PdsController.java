package com.spring.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.PdsVO;
import com.spring.application.service.PdsService;

@RequestMapping("/pds")
@Controller
public class PdsController {
    @Autowired
    private PdsService pdsService;

    @GetMapping("/list")
    public String list(@ModelAttribute PageMaker pageMaker, Model mnv) throws Exception{
        String url = "/pds/list";
        List<PdsVO> pdsList = pdsService.searchlist(pageMaker);

        mnv.addAttribute("pdsList", pdsList);
        
        
        return url;
    }
    @GetMapping("/registForm")
    public String registForm() throws Exception{
        String url = "/pds/regist";

        return url;
    }
}
