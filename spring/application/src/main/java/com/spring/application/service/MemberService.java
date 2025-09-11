package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;



import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;


public interface MemberService {
    //회원목록
    List<MemberVO> list(PageMaker pageMaker) throws SQLException;
    //회원상세
    //회원수정
    MemberVO getMember(String id) throws SQLException;
    void modify(MemberVO member) throws SQLException;
    //회원 등록
    void regist(MemberVO member) throws SQLException;
    //회원 삭제
    void remove(String id) throws SQLException;

}
