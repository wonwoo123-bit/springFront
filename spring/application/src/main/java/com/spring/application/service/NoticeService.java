package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.NoticeVO;

public interface NoticeService {
    //공지목록
    public List<NoticeVO> list(PageMaker pageMaker) throws SQLException;
    //공지상세
    public NoticeVO detail(int nno) throws SQLException;
    //공지수정
    public NoticeVO getNotice(int nno) throws SQLException;
    public void modify(NoticeVO notice) throws SQLException;
    //공지 등록
    public void regist(NoticeVO notice) throws SQLException;
    //공지 삭제
    public void remove(int nno) throws SQLException;
}
