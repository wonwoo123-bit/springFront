package com.spring.application.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        sqlSession.delete("Notice-Mapper.deleteNotice",nno);
        
    }

    @Override
    public void increaseViewCount(int nno) throws SQLException {
        sqlSession.update("Notice-Mapper.increaseViewCount",nno);
        
    }

    @Override
    public void insertNotice(NoticeVO notice) throws SQLException {
        sqlSession.insert("Notice-Mapper.insertNotice",notice);
        
    }

    @Override
    public NoticeVO selectNoticeByNno(int nno) throws SQLException {
        NoticeVO notice = sqlSession.selectOne("Notice-Mapper.selectNoticeByNno",nno);
        return notice;
    }

    @Override
    public int selectNoticeSequenceNextValue() throws SQLException {
        int nno = sqlSession.selectOne("Notice-Mapper.selectNoticeSequenceNextValue");
        return nno;
    }

    @Override
    public List<NoticeVO> selectSearchNoticeList(PageMaker pageMaker) throws SQLException {
        int startRow = pageMaker.getStartRow();
        int endRow = startRow + pageMaker.getPerPageNum();

        Map<String, Object> dataParam = new HashMap<String, Object>();
        dataParam.put("startRow", startRow);
        dataParam.put("endRow", endRow);
        dataParam.put("searchType", pageMaker.getSearchType());
        dataParam.put("keyword", pageMaker.getKeyword());

        List<NoticeVO> noticeList
        = sqlSession.selectList("Notice-Mapper.selectSearchNoticeList",dataParam);

        return noticeList;
    }

    @Override
    public int selectSearchNoticeListCount(PageMaker pageMaker) throws SQLException {
        int count = sqlSession.selectOne("Notice-Mapper.selectSearchNoticeListCount",pageMaker);
        return count;
    }

    @Override
    public void updateNotice(NoticeVO notice) throws SQLException {
        sqlSession.update("Notice-Mapper.updateNotice",notice);
        
    }
    

}
