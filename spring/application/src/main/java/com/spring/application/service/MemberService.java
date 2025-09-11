package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;


public interface MemberService {
    //회원목록
    public List<MemberVO> list(PageMaker pageMaker) throws SQLException;
    //회원상세
    public MemberVO detail(String id) throws SQLException;
    //회원수정
    public MemberVO getNotice(String id) throws SQLException;
    public void modify(MemberVO member) throws SQLException;
    //회원 등록
    public void regist(MemberVO member) throws SQLException;
    //회원 삭제
    public void remove(String id) throws SQLException;

}
