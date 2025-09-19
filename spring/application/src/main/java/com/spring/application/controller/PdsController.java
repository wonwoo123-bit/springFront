package com.spring.application.controller;

import java.nio.file.Paths;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.io.File; // ← 이걸 사용
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriUtils;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.application.command.PageMaker;
import com.spring.application.command.PdsModifyCommand;
import com.spring.application.command.PdsRegistCommand;
import com.spring.application.dto.AttachVO;
import com.spring.application.dto.MemberVO;
import com.spring.application.dto.PdsVO;
import com.spring.application.service.PdsService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.Resource; // ← 이걸 사용
import org.springframework.core.io.UrlResource;

@RequestMapping("/pds")
@Controller
public class PdsController {
    @Autowired
    private PdsService pdsService;

    @Value("${savedPath.pds}")
    String fileUploadPath;

    @GetMapping("/list")
    public String list(@ModelAttribute PageMaker pageMaker, Model mnv) throws Exception {
        String url = "/pds/list";
        List<PdsVO> pdsList = pdsService.searchlist(pageMaker);

        mnv.addAttribute("pdsList", pdsList);

        return url;
    }

    @GetMapping("/registForm")
    public String registForm() throws Exception {
        String url = "/pds/regist";

        return url;
    }

    @PostMapping(value = "/regist", produces = "text/plain;charset=utf-8")
    public ModelAndView regist(PdsRegistCommand cmd, ModelAndView mnv) throws Exception {
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

    private List<AttachVO> saveFileToAttaches(List<MultipartFile> multiFiles, String savePath) throws Exception {
        List<AttachVO> attachList = null;

        if (multiFiles == null)
            return null;

        attachList = new ArrayList<AttachVO>();
        for (MultipartFile multi : multiFiles) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = uuid + "$$" + multi.getOriginalFilename();

            java.io.File target = new java.io.File(savePath, fileName);
            target.mkdirs();
            multi.transferTo(target);

            AttachVO attach = new AttachVO();
            attach.setUploadPath(savePath);
            attach.setFileName(fileName);
            attach.setFileType(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());

            attachList.add(attach);
        }
        return attachList;

    }

    @GetMapping("/detail")
    public ModelAndView detail(int pno, HttpServletRequest res, ModelAndView mnv) throws Exception {
        String url = "/pds/detail";

        PdsVO pds = pdsService.getPds(pno);

        ServletContext ctx = res.getServletContext();
        HttpSession session = res.getSession();
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
        String key = "pds: " + loginUser.getId() + pno;

        Object value = ctx.getAttribute(key);
        if (value == null) {
            pdsService.increaseViewCnt(pno);
            ctx.setAttribute(key, "");
        }

        mnv.addObject("pds", pds);
        mnv.setViewName(url);

        return mnv;
    }

    @GetMapping("/getFile")
    @ResponseBody
    public ResponseEntity<Resource> getFile(int ano) throws Exception {
        AttachVO attach = pdsService.getAttachByAno(ano);

        String filename = attach.getFileName();
        String filePath = attach.getUploadPath() + File.separator + filename;

        Resource resource = new UrlResource(Paths.get(filePath).toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + UriUtils.encode(filename.split("\\$\\$")[1], "UTF-8")
                                + "\"")
                .body(resource);
    }

    @GetMapping("/modifyForm")
    public ModelAndView modifyForm(int pno, ModelAndView mnv) throws Exception {
        String url = "pds/modify";

        PdsVO pds = pdsService.getPds(pno);

        mnv.addObject("pds", pds);
        mnv.setViewName(url);

        return mnv;
    }

    @PostMapping("/modify")
    public ModelAndView modify(PdsModifyCommand cmd, ModelAndView mnv)throws Exception{
        String url = "/pds/modify_success";
        if(cmd.getDeleteFile() != null && cmd.getDeleteFile().length>0){
            for(int ano : cmd.getDeleteFile()){
                AttachVO attach = pdsService.getAttachByAno(ano);

                //저장경로에 저장해둔 파일이름 따오기
                String savedPath = attach.getUploadPath().replace("/", File.separator);
                File deleteFile = new File(savedPath, attach.getFileName());

                if (deleteFile.exists()) {
                    deleteFile.delete(); // 저장경로로 지정해둔 파일 삭제
                    
                }
                pdsService.removeAttachByAno(ano); //DB에서 삭제 시키는것
            }
        }
        //파일저장해주기
        List<AttachVO> attachList = saveFileToAttaches(cmd.getUploadFile(), fileUploadPath);
        //PdsVO에 바뀌어 저장할 값 세팅하기
        PdsVO pds = cmd.topdsVO();
        pds.setAttachList(attachList);
        pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
        //DB에 저장 아래3줄
        pdsService.modify(pds);

        mnv.setViewName(url);

        return mnv;
    }

    @GetMapping("/remove")
    public ModelAndView remove(int pno, ModelAndView mnv)throws Exception{
        String url = "pds/remove_success";
        
        pdsService.remove(pno);

        mnv.setViewName(url);
        return mnv;
    }


}
