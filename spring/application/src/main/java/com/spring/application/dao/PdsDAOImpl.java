package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.PdsVO;

@Repository
public class PdsDAOImpl implements PdsDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void deletePds(int pno) throws SQLException {
        sqlSession.delete("Pds-Mapper.deletePds",pno);
        
    }

    @Override
    public void increaseViewCnt(int pno) throws SQLException {
        sqlSession.update("Pds-Mapper.increaseViewCnt",pno);
        
    }

    @Override
    public void insertPds(PdsVO pds) throws SQLException {
        sqlSession.insert("Pds-Mapper.insertPds",pds);
        
    }

    @Override
    public PdsVO selectPdsByPno(int pno) throws SQLException {
        PdsVO pds = sqlSession.selectOne("Pds-Mapper.selectPdsByBno",pno);
        return pds;
    }

    @Override
    public int selectPdsSeqNext() throws SQLException {
        int seq = sqlSession.selectOne("Pds-Mapper.selectPdsSeqNext");
        return seq;
    }

    @Override
    public List<PdsVO> selectSearchPdsList(PageMaker pageMaker) throws SQLException {
        int startRow = pageMaker.getStartRow();
        int endRow = pageMaker.getPerPageNum();

        RowBounds rows = new RowBounds(startRow, endRow);
        return sqlSession.selectList("Pds-Mapper.selectSearchPdsList",pageMaker,rows);
    }

    @Override
    public int selectSearchPdsListCount(PageMaker pageMaker) throws SQLException {
        int cnt = sqlSession.selectOne("Pds-Mapper.selectSearchPdsListCount",pageMaker);
        return cnt;
    }

    @Override
    public void updatePds(PdsVO pds) throws SQLException {
        sqlSession.update("Pds-Mapper.updatePds",pds);
        
    }

    
}
