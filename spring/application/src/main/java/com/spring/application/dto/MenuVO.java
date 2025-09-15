package com.spring.application.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MenuVO {
    private String mcode; // 메뉴 코드
    private String mname; // 메뉴 이름
    private String murl; // 메뉴 url
    private String micon; // 메뉴 아이콘
    private String jText; // javaScript
    private String upcode; // 상위메뉴 코드
    private int mlevel; // 메뉴 레벨
    private int isNav;

    private List<MenuVO> subMenuList;
}