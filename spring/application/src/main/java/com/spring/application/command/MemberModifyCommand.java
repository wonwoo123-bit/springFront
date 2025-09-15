package com.spring.application.command;

import com.spring.application.dto.MemberVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberModifyCommand extends MemberRegistCommand{
    private String oldPirture;
    
    @Override
    public MemberVO toMemberVO() {
        MemberVO member = super.toMemberVO();
        member.setPicture(this.oldPirture);

        return member;
    }
}
