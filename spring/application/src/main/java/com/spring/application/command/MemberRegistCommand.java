package com.spring.application.command;



import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.spring.application.dto.MemberVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter

public class MemberRegistCommand {
    private String id;
    private String pwd;
    private String email;
    private MultipartFile picture;
    private String[] phone;
    private String name="---";
    private String authorities;
    private String address;
    
    public MemberVO toMemberVO(){

        MemberVO member = MemberVO.builder()
        .email(email)
        .id(id)
        .name(name)
        .enabled(1)
        .pwd(pwd)
        .regDate(new Date())
        .authorities(authorities)
        .address(address)
        .build();

        String phoneTemp = "";
        for(String p : phone){
            phoneTemp += p;
        }
        member.setPhone(phoneTemp);

        return member;
    }
    
    
}
