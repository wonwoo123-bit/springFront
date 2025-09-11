package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;

@Repository
public class MemberServiceImpl implements MemberService{

    @Override
    public MemberVO detail(String id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MemberVO getNotice(String id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MemberVO> list(PageMaker pageMaker) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void modify(MemberVO member) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void regist(MemberVO member) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(String id) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    

}
