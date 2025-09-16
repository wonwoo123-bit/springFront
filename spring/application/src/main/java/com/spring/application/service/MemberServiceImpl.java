package com.spring.application.service;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.application.command.PageMaker;
import com.spring.application.dao.MemberDAO;
import com.spring.application.dto.MemberVO;
import com.spring.application.exeption.InvalidPasswordException;
import com.spring.application.exeption.NotFoundIDException;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberDAO memberDAO;

    

    @Override
    public MemberVO getMember(String id) throws SQLException {
        
        MemberVO member = memberDAO.selectMemberById(id);
        return member;
    }

    @Override
    @Transactional
    public List<MemberVO> list(PageMaker pageMaker) throws SQLException {
        List<MemberVO> memberList = memberDAO.selectSearchMemberList(pageMaker);

        pageMaker.setTotalCount(memberDAO.selectSearchMemberListCount(pageMaker));
        return memberList;
    }

    @Override
    public void modify(MemberVO member) throws SQLException {
        memberDAO.updateMember(member);
        
    }

    @Override
    public void regist(MemberVO member) throws SQLException {
        memberDAO.insertMember(member);
        
    }

    @Override
    public void remove(String id) throws SQLException {
        memberDAO.deleteMember(id);
        
    }

    @Override
    public MemberVO login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException {
        MemberVO member = memberDAO.selectMemberById(id);

        if(member== null)throw new NotFoundIDException();
        if (!member.getPwd().equals(pwd))throw new InvalidPasswordException(); 
                        
        return member;
    }
    
    

}
