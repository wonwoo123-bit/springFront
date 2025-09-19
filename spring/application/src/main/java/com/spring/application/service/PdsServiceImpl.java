package com.spring.application.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.application.command.PageMaker;
import com.spring.application.dao.AttachDAO;
import com.spring.application.dao.PdsDAO;
import com.spring.application.dto.AttachVO;
import com.spring.application.dto.PdsVO;

@Service
public class PdsServiceImpl implements PdsService{
    @Autowired
    private PdsDAO pdsDAO;

    @Autowired 
    private AttachDAO attachDAO;

    @Override
    public AttachVO getAttachByAno(int ano) throws SQLException {
        
        return attachDAO.selectAttachByAno(ano);
    }

    @Override
    public PdsVO getPds(int pno) throws SQLException {
        PdsVO pds = pdsDAO.selectPdsByPno(pno);
        List<AttachVO> attachList = attachDAO.selectAttachByPno(pno);
        pds.setAttachList(attachList);
        return pds;
    }

    @Override
    public void increaseViewCnt(int pno) throws SQLException {
        pdsDAO.increaseViewCount(pno);
        
    }

    @Override
    @Transactional
    public void modify(PdsVO pds) throws SQLException {
        pdsDAO.updatePds(pds);

        int pno = pds.getPno();

        List<AttachVO> attachList = pds.getAttachList();
        if (attachList!=null) for(AttachVO attach : attachList){
            attach.setPno(pno);
            attach.setAttacher(pds.getWriter());
            attachDAO.insertAttach(attach);
        }
        
    }

    @Override
    @Transactional
    public void regist(PdsVO pds) throws SQLException {
        int pno = pdsDAO.selectPdsSeqNext();
        pds.setPno(pno);
        pdsDAO.insertPds(pds);

        List<AttachVO> attachlist = pds.getAttachList();
        if (attachlist!=null)for(AttachVO attach : attachlist) {
            attach.setPno(pno);
            attach.setAttacher(pds.getWriter());
            attachDAO.insertAttach(attach);
        }
    }

    @Override
    @Transactional
    public void remove(int pno) throws SQLException {
        List<AttachVO> attachList = attachDAO.selectAttachByPno(pno);
        if(attachList != null){
            for(AttachVO attach : attachList){
                File target = new File(attach.getUploadPath(),attach.getFileName());
                if ((target.exists())) {
                    target.delete();
                    
                }
            }
        }
        attachDAO.deleteAttachAll(pno);
        pdsDAO.deletePds(pno);
    }

    @Override
    public void removeAttachByAno(int ano) throws SQLException {
        attachDAO.deleteAttach(ano);
        
    }

    @Override
    public List<PdsVO> searchlist(PageMaker pageMaker) throws SQLException {
        List<PdsVO> pdsList = pdsDAO.selectSearchPdsList(pageMaker);
        //첨부파일 내용 추가
        if (pdsList != null) for(PdsVO pds : pdsList){
            int pno = pds.getPno();
            List<AttachVO> attachList = attachDAO.selectAttachByPno(pno);
            pds.setAttachList(attachList);
        }

        //pageMaker 세팅
        int listTtc = pdsDAO.selectSearchPdsListCount(pageMaker);
        pageMaker.setTotalCount(listTtc);
        
        return pdsList;
    }

    

}
