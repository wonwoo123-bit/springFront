package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.application.command.PageMaker;
import com.spring.application.dao.PdsDAO;
import com.spring.application.dto.PdsVO;

@Service
public class PdsServiceImpl implements PdsService{
    @Autowired
    private PdsDAO pdsDAO;

    @Override
    @Transactional
    public PdsVO detail(int pno) throws SQLException {
        PdsVO pds = pdsDAO.selectPdsByPno(pno);
        pdsDAO.increaseViewCount(pno);
        return pds;
    }

    @Override
    public PdsVO getPds(int pno) throws SQLException {
        PdsVO pds = pdsDAO.selectPdsByPno(pno);
        return pds;
    }

    @Override
    public List<PdsVO> list(PageMaker pageMaker) throws SQLException {
        List<PdsVO> pdsList = pdsDAO.selectSearchPdsList(pageMaker);
        // 화면 출력용 pagemaker양식에 맞게 총 개수 추출
        int ttc = pdsDAO.selectSearchPdsListCount(pageMaker);
        // 추출 적용
        pageMaker.setTotalCount(ttc);

        return pdsList;
    }

    @Override
    public void modify(PdsVO pds) throws SQLException {
        pdsDAO.updatePds(pds);
        
    }

    @Override
    public void regist(PdsVO pds) throws SQLException {
        pdsDAO.insertPds(pds);
        
    }

    @Override
    public void remove(int pno) throws SQLException {
        pdsDAO.deletePds(pno);
        
    }

    

}
