package com.spring.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MenuVO {
    private String mcode;
    private String mname;
    private String murl;
    private String micon;
    private String jText;
    private String upcode;
    private int mlever;
    private int isNav;

}
