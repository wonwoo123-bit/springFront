package com.spring.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberVO {
    private String id;
    private String pwd;
    private String email;
    private String picture;
    private int enabled;
    private Date regDate;
    private String phone;
    private String name="---";
    private String register;
    private String address;
    private String authorities;

}
