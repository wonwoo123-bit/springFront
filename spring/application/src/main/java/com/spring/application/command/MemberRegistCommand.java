package com.spring.application.command;



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
    private String picture;
    private String phone;
    private String name="---";
    private String authorities;
    
    public MemberVO toMemberVO(){
        MemberVO member = new MemberVO();
        member.setId(id);
        member.setPwd(pwd);
        member.setEmail(email);
        member.setPicture(picture);
        member.setPhone(phone);
        member.setName(name);
        member.setAuthorities(authorities);
        return member;
    }
    
    
}
