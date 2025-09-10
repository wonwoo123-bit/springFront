package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.application.command.PageMaker;
import com.spring.application.dao.NoticeDAO;
import com.spring.application.dto.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeDAO noticeDAO;

    @Override
    public NoticeVO detail(int nno) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NoticeVO getNotice(int nno) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<NoticeVO> list(PageMaker pageMaker) throws SQLException {
        List<NoticeVO> noticeList = noticeDAO.selectSearchNoticeList(pageMaker);
        //전체 공지사항 개수
        int totalCount = noticeDAO.selectSearchNoticeListCount(pageMaker);
        //pageMaker 세팅 (totalcount 메서드 사용)
        pageMaker.setTotalCount(totalCount);

        return noticeList;
    }

    @Override
    public void modify(NoticeVO notice) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    @Transactional
    public void regist(NoticeVO notice) throws SQLException {
        int nno = noticeDAO.selectNoticeSequenceNextValue();
        notice.setNno(nno);
        noticeDAO.insertNotice(notice);
        
    }

    @Override
    public void remove(int nno) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    

}
