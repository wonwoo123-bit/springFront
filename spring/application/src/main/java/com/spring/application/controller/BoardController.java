package com.spring.application.controller;

import java.rmi.server.ExportException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.application.command.BoardModifyCommand;
import com.spring.application.command.BoardRegistCommand;
import com.spring.application.command.PageMaker;
import com.spring.application.dto.BoardVO;
import com.spring.application.dto.MemberVO;
import com.spring.application.service.BoardService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/board")
public record BoardController(BoardService boardService){

    @GetMapping("/main")
    public void main(){}

    @GetMapping("/list")
    public String list(@ModelAttribute(name = "pageMaker") PageMaker pageMaker, Model model) throws Exception{
        String url = "/board/list";
            
        model.addAttribute("boardList", boardService.list(pageMaker));
        
        return url;
    }

    @GetMapping("/detail")
    public String detail(int bno, Model model, HttpServletRequest request) throws Exception{
        String url = "board/detail";
        ServletContext application = request.getServletContext();

        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");

        String key = "board:"+loginUser.getId()+bno;
        Object value = application.getAttribute(key);

        BoardVO board = null;
        if (value != null) {
            board = boardService.getBoard(bno);
            
        }else{
            board = boardService.detail(bno);
            application.setAttribute(key, "");
        }
        model.addAttribute("board", board);


        return url;
    }
    @GetMapping("/registForm")
    public String registForm()throws Exception{
        String url = "/board/regist";
        return url;
    }
    @PostMapping("/regist")
    public String regist(BoardRegistCommand boardRegistCommand,HttpServletRequest request)throws Exception{
        String url = "board/regist_success";

        HttpSession session = request.getSession();
        MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");

        boardRegistCommand.setWiter(loginUser.getId());
        BoardVO board = boardRegistCommand.toBoardVO();
        boardService.regist(board);
        
        return url;
    }
    @GetMapping("/modifyForm")
    public ModelAndView modifyForm(Integer bno, ModelAndView mnv) throws Exception{
        String url ="board/modify";

        BoardVO board = boardService.getBoard(bno);

        mnv.addObject("board", board);
        mnv.setViewName(url);

        return mnv;
    }

    @PostMapping("/modify")
    public String modify(BoardModifyCommand bmc, Model model)throws Exception{
        String url = "redirect:/board/detail?bno="+bmc.getBno();

        boardService.modify(bmc.toBoardVO());
        model.addAttribute("bno", bmc.getBno());
        return url;
    }

    @GetMapping(value = "/remove")
    public String remove(int bno) throws Exception{
        String url = "board/remove_success";
        boardService.remove(bno);
        
        return url;
    }

    
}
