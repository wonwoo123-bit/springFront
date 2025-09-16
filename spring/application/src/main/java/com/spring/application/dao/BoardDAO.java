package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.BoardVO;

public interface BoardDAO {
    List<BoardVO> selectSearchBoardVOList(PageMaker pageMaker) throws SQLException;

    int selectSearchBoardVOListCount(PageMaker pageMaker) throws SQLException;

    BoardVO selectBoardVOByBno(int bno) throws SQLException;

    void increaseViewCount (int bno) throws SQLException;

    int selectBoardVOSequenceNextValue () throws SQLException;

    void insertBoardVO (BoardVO BoardVO) throws SQLException;

    void updateBoardVO (BoardVO BoardVO) throws SQLException;

    void deleteBoardVO (int bno) throws SQLException;

}
