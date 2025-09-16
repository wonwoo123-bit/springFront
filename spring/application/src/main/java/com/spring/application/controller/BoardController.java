package com.spring.application.controller;

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
import jakarta.servlet.http.HttpServletRequest;


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
        String key = "board:"+bno;

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
    
}
