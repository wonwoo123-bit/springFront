package com.spring.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class MemberVO {
    private String id;
    private String pwd;
    private String email;
    private String pitcure;
    private int enabled;
    private Date regDate;
    private String phone;
    private String name;
    private String register;
    private String address;
    private String authority;
}
