package com.spring.application.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void deleteMember(String id) throws SQLException {
        sqlSession.delete("Member-Mapper.deleteMember",id);
        
    }

    @Override
    public void insertMember(MemberVO member) throws SQLException {
        sqlSession.insert("Member-Mapper.insertMember",member);
        
    }

    @Override
    public MemberVO selectMemberById(String id) throws SQLException {
        return sqlSession.selectOne("Member-Mapper.selectMemberById",id); 
    }

    @Override
    public List<MemberVO> selectSearchMemberList(PageMaker pageMaker) throws SQLException {
       int offset = pageMaker.getStartRow() -1;
       int limit = pageMaker.getPerPageNum();

       RowBounds rows = new RowBounds(offset, limit);

        return sqlSession.selectList("Member-Mapper.selectSearchMemberList",pageMaker,rows);
    }

    @Override
    public int selectSearchMemberListCount(PageMaker pageMaker) throws SQLException {
        return sqlSession.selectOne("Member-Mapper.selectSearchMemberListCount",pageMaker);
    }

    @Override
    public void updateMember(MemberVO member) throws SQLException {
        sqlSession.update("Member-Mapper.updateMember",member);
        
    }

   
}
