package com.spring.application.controller;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.BoardVO;
import com.spring.application.service.BoardService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board")
public record BoardController(BoardService boardService){

    @GetMapping("/main")
    public String list(@ModelAttribute(name = "pageMaker") PageMaker pageMaker, Model model) throws Exception{
        String url = "/board/main";
            
        List<BoardVO> boardList = boardService.list(pageMaker);
        
        model.addAttribute("boardList", boardList);


        return url;
    }

    @GetMapping("/read")
    public String detail(int bno, Model model, HttpServletRequest request) throws Exception{
        String url = "board/read";
        ServletContext application = request.getServletContext();
        String key = "board:"+bno;


        return url;
    }
    
}
