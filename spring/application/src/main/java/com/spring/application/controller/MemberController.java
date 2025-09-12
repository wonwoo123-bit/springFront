package com.spring.application.controller;

import java.beans.Expression;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.application.command.MemberRegistCommand;
import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;
import com.spring.application.service.MemberService;

@Controller
@RequestMapping("/member")
public record MemberController(MemberService memberService , @Value("${member.picture}" )String picturePath) {

    @GetMapping("/list")
    public void list(@ModelAttribute(name = "pageMaker") PageMaker pageMaker, Model model) throws Exception {
        List<MemberVO> memberList = memberService.list(pageMaker);

        model.addAttribute("memberList", memberList);
    }

    @GetMapping("/registForm")
    public String registForm() {
        String url = "/member/regist";
        return url;
    }

    @GetMapping("/idCheck")
    @ResponseBody
    public String idCheck(String id) throws Exception {
        String message = "duplicated";

        MemberVO member = memberService.getMember(id);

        if (member == null) {
            message = "";
        }
        return message;
    }

    @PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
    public String regist(MemberRegistCommand regCommand) throws Exception{
        String url = "/member/regist_success";
        //파일저장 :함수만들어서 > savePicture()
        MultipartFile multi = regCommand.getPicture();
        String fileName = savePicture(null,multi);
        
        //DB저장
        MemberVO member = regCommand.toMemberVO();
        member.setPicture(fileName);
        member.setRegister("mimi");

        memberService.regist(member);

        return url;
    }
    public String savePicture(String oldPicture, MultipartFile multi)throws Exception{
        //저장 파일명
        String fileName = null;

        String uuid = UUID.randomUUID().toString().replace("-", "")+".jpg";
        fileName = uuid + "$$" + multi.getOriginalFilename();

        String uploadPath = this.picturePath;

        File storeFile = new File(uploadPath, fileName);
        storeFile.mkdirs();
        multi.transferTo(storeFile);

        if(oldPicture != null && !oldPicture.isEmpty()){
            File oldFile = new File(uploadPath,oldPicture);
            if(oldFile.exists()){
                oldFile.delete();
            }
        }

        return fileName;
    }
    @GetMapping("/detail")
    public ModelAndView detail(String id, ModelAndView mnv) throws Exception{
        String url = "/member/detail";
        
        MemberVO member = memberService.getMember(id);

        mnv.addObject("member", member);
        mnv.setViewName(url);
        return mnv;
    }

    @GetMapping("/getPicture")
    @ResponseBody
    public ResponseEntity<byte[]> getPicture(String id) throws Exception{
        ResponseEntity entity = null;
        MemberVO member = memberService.getMember(id);

        if(member == null)
        return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);

        String picture = member.getPicture();
        String imgPath = this.picturePath;
        InputStream in = null;
        try{
            in = new FileInputStream(new File(imgPath,picture));
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),HttpStatus.OK);
            return entity;
        }finally{
            if(in != null)
            in.close();
        }
    }

    @GetMapping("/modifyForm")
    public ModelAndView modifyForm(String id, ModelAndView mnv) throws Exception{
        String url = "/member/modify";

        MemberVO member = memberService.getMember(id);

        mnv.addObject("member", member);
        mnv.setViewName(url);
        return mnv;
    }

}
