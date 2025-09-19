package com.spring.application.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.application.command.PageMaker;
import com.spring.application.command.PdsRegistCommand;
import com.spring.application.dto.AttachVO;
import com.spring.application.dto.MemberVO;
import com.spring.application.dto.PdsVO;
import com.spring.application.service.PdsService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;



@RequestMapping("/pds")
@Controller
public class PdsController {
    @Autowired
    private PdsService pdsService;

    @Value("${savedPath.pds}")
    String fileUploadPath;

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
    @PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
    public ModelAndView regist(PdsRegistCommand cmd, ModelAndView mnv) throws Exception{
        String url = "/pds/regist_success";

        List<MultipartFile> multiFiles = cmd.getUploadFile();
        String savedPath = this.fileUploadPath;

        List<AttachVO> attachList = saveFileToAttaches(multiFiles, savedPath);

        PdsVO pds = cmd.topdsVO();
        pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
        pds.setAttachList(attachList);

        pdsService.regist(pds);

        mnv.setViewName(url);
        
        return mnv;
    }

    private List<AttachVO> saveFileToAttaches(List<MultipartFile> multiFiles, String savePath) throws Exception{
        List<AttachVO> attachList = null;

        if (multiFiles == null) return null;

        attachList = new ArrayList<AttachVO>();
            for(MultipartFile multi : multiFiles){
                String uuid = UUID.randomUUID().toString().replace("-", "");
                String fileName = uuid + "$$" + multi.getOriginalFilename();

                File target = new File(savePath, fileName);
                target.mkdirs();
                multi.transferTo(target);

                AttachVO attach = new AttachVO();
                attach.setUploadPath(savePath);
                attach.setFileName(fileName);
                attach.setFileType(fileName.substring(fileName.lastIndexOf('.')+1).toUpperCase());

                attachList.add(attach);
            }
            return attachList;
    
    }
    @GetMapping("/detail")
    public ModelAndView detail(int pno, HttpServletRequest res, ModelAndView mnv) throws Exception{
        String url = "/pds/detail";

        PdsVO pds = pdsService.getPds(pno);

        ServletContext ctx = res.getServletContext();
        HttpSession session = res.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
        String key = "pds: " + loginUser.getId()+pno;

        Object value = ctx.getAttribute(key);
        if(value == null){
            pdsService.increaseViewCnt(pno);
            ctx.setAttribute(key, "");
        }

        mnv.addObject("pds", pds);
        mnv.setViewName(url);

        return mnv;
    }
    
}
