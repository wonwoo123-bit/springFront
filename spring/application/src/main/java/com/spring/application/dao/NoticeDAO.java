package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.NoticeVO;

public interface NoticeDAO {
    List<NoticeVO> selectSearchNoticeList(PageMaker pageMaker) throws SQLException;

    int selectSearchNoticeListCount(PageMaker pageMaker) throws SQLException;

    NoticeVO selectNoticeByNno(int nno) throws SQLException;

    void increaseViewCount (int nno) throws SQLException;

    int selectNoticeSequenceNextValue () throws SQLException;

    void insertNotice (NoticeVO Notice) throws SQLException;

    void updateNotice (NoticeVO Notice) throws SQLException;

    void deleteNotice (int nno) throws SQLException;
}
