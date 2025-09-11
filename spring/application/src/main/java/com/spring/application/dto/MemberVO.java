package com.spring.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Builder
public class MemberVO {
    private String id;
    private String pwd;
    private String email;
    private String pitcure;
    private int enabled;
    private Date regDate;
    private String phone;
    private String name="---";
    private String register;
    private String address;
    private String authority;
}
