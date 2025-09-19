package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.application.dto.AttachVO;

@Repository
public class AttachDAOImpl implements AttachDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void deleteAttach(int ano) throws SQLException {
        sqlSession.delete("Attach-Mapper.deleteAttach",ano);
        
    }

    @Override
    public void deleteAttachAll(int pno) throws SQLException {
        sqlSession.delete("Attach-Mapper.deleteAttachAll",pno);
        
    }

    @Override
    public void insertAttach(AttachVO attach) throws SQLException {
        sqlSession.insert("Attach-Mapper.insertAttach",attach);
        
    }

    @Override
    public AttachVO selectAttachByAno(int ano) throws SQLException {
        
        return sqlSession.selectOne("Attach-Mapper.selectAttachByAno",ano);
    }

    @Override
    public List<AttachVO> selectAttachByPno(int pno) throws SQLException {
        
        return sqlSession.selectList("Attach-Mapper.selectAttachByPno",pno);
    }
    
}
