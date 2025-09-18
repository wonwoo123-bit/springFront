package com.spring.application.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void increaseViewCount(int pno) throws SQLException {
        sqlSession.selectOne("Pds-Mapper.increaseViewCount",pno);
        
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
        int pno = sqlSession.selectOne("Pds-Mapper.selectPdsSeqNext");
        return pno;
    }

    @Override
    public List<PdsVO> selectSearchPdsList(PageMaker pageMaker) throws SQLException {
        int startRow = pageMaker.getStartRow();
        int endRow = startRow + pageMaker.getPerPageNum();

        Map<String, Object> dataParam = new HashMap<String,Object>();
        dataParam.put("startRow", startRow);
        dataParam.put("endRow", endRow);
        dataParam.put("searchType", pageMaker.getSearchType());
        dataParam.put("keyword", pageMaker.getKeyword());

        List<PdsVO> pdsList = sqlSession.selectList("Pds-Mapper.selectSearchPdsList",dataParam);
        return pdsList;
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
