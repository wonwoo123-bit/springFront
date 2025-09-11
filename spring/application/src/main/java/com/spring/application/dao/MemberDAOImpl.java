package com.spring.application.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        MemberVO member = sqlSession.selectOne("Member-Mapper.selectMemberById",id);
        return member;
    }

    @Override
    public List<MemberVO> selectSearchMemberList(PageMaker pageMaker) throws SQLException {
        int startRow = pageMaker.getStartRow();
        int endRow = startRow + pageMaker.getPerPageNum();

        Map<String, Object> dataParam = new HashMap<String, Object>();
        dataParam.put("startRow", startRow);
        dataParam.put("endRow", endRow);
        dataParam.put("searchType", pageMaker.getSearchType());
        dataParam.put("keyword", pageMaker.getKeyword());

        List<MemberVO> memberList
        = sqlSession.selectList("Member-Mapper.selectSearchMemberList",dataParam);

        return memberList;
    }

    @Override
    public int selectSearchMemberListCount(PageMaker pageMaker) throws SQLException {
        int cnt = sqlSession.selectOne("Member-Mapper.selectSearchMemberListCount",pageMaker);
        return cnt;
    }

    @Override
    public void updateMember(MemberVO member) throws SQLException {
        sqlSession.update("Member-Mapper.updateMember",member);
        
    }

   
}
