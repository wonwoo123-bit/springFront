package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.BoardVO;

public interface BoardService {
    //공지목록
    List<BoardVO> list(PageMaker pageMaker) throws SQLException;
    //공지상세
    BoardVO detail(int bno) throws SQLException;
    //공지수정
    BoardVO getBoard(int bno) throws SQLException;
    void modify(BoardVO board) throws SQLException;
    //공지 등록
    void regist(BoardVO board) throws SQLException;
    //공지 삭제
    void remove(int bno) throws SQLException;
}
