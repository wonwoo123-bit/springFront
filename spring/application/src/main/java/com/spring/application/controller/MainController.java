package com.spring.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.application.dto.MenuVO;
import com.spring.application.service.MenuService;

@Controller
@RequestMapping("/")
public record MainController(MenuService menuService) {

    @GetMapping("/index.html")
    public String getMethodName(@RequestParam(defaultValue = "M000000") String mcode, Model model) throws Exception {
        String url = "/main/main";

        model.addAttribute("menuList", menuService.getMenuList());

        MenuVO menu = menuService.getMenuByMcode(mcode);
        model.addAttribute("menu", menu);

        return url;
    }

    @GetMapping("/home")
    public void home(){}

}