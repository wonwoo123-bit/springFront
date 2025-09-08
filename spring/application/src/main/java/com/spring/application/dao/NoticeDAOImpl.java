package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.NoticeVO;

@Repository
public class NoticeDAOImpl implements NoticeDAO{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void deleteNotice(int nno) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void increaseViewCount(int nno) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertNotice(NoticeVO Notice) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public NoticeVO selectNoticeByNno(int nno) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int selectNoticeSequenceNextValue() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<NoticeVO> selectSearchNoticeList(PageMaker pageMaker) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int selectSearchNoticeListCount(PageMaker pageMaker) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void updateNotice(NoticeVO Notice) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    

}
