package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Override
    public void deleteBoardVO(int bno) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void increaseViewCount(int bno) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertBoardVO(BoardVO BoardVO) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public BoardVO selectBoardVOByBno(int bno) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int selectBoardVOSequenceNextValue() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<BoardVO> selectSearchBoardVOList(PageMaker pageMaker) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int selectSearchBoardVOListCount(PageMaker pageMaker) throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void updateBoardVO(BoardVO BoardVO) throws SQLException {
        // TODO Auto-generated method stub
        
    }
    

}
