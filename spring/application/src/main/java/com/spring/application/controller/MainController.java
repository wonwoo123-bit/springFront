package com.spring.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/index.html")
    public String sdf(){
        String url = "/main/main";

        return url;
    }

}
