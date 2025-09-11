package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;

public interface MemberDAO {
    List<MemberVO> selectSearchMemberList(PageMaker pageMaker) throws SQLException;

    int selectSearchMemberListCount(PageMaker pageMaker) throws SQLException;

    MemberVO selectMemberById(String id) throws SQLException;

    void insertMember (MemberVO member) throws SQLException;

    void updateMember (MemberVO member) throws SQLException;

    void deleteMember (String id) throws SQLException;
}
