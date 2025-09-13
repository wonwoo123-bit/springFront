package com.spring.application.controller;

public class MemberModifyCommand extends MemberRegistCommand {
    
    @Override
    public MemberVO toMemberVO() {
        MemberVO member = super.toMemberVO();
        
        return member;
    }  

}
