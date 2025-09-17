package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;



import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;
import com.spring.application.exeption.InvalidPasswordException;
import com.spring.application.exeption.NotFoundIDException;


public interface MemberService {
    //회원목록
    List<MemberVO> list(PageMaker pageMaker) throws SQLException;
    //회원상세
    MemberVO getMember(String id) throws SQLException;
    //회원수정
    void modify(MemberVO member) throws SQLException;
    //회원 등록
    void regist(MemberVO member) throws SQLException;
    //회원 삭제
    void remove(String id) throws SQLException;
    //login
    MemberVO login(String id, String pwd)throws SQLException,NotFoundIDException,InvalidPasswordException;

}
