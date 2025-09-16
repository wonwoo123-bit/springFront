package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.application.command.PageMaker;
import com.spring.application.dao.BoardDAO;
import com.spring.application.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardDAO boardDAO;

    @Override
    public BoardVO detail(int bno) throws SQLException {
        BoardVO board = boardDAO.selectBoardByBno(bno);
        boardDAO.increaseViewCount(bno);
        return board;
    }

    @Override
    public BoardVO getBoard(int bno) throws SQLException {
        BoardVO board = boardDAO.selectBoardByBno(bno);
        return board;
    }

    @Override
    public List<BoardVO> list(PageMaker pageMaker) throws SQLException {
        List<BoardVO> boardList = boardDAO.selectSearchBoardVOList(pageMaker);

        //reply 개수 세팅
        
        //pageMaker세팅(화면 출력 나올때 사용하는 용도)
        int totalCount = boardDAO.selectSearchBoardListCount(pageMaker);

        //pageMaker에 값 할당
        pageMaker.setTotalCount(totalCount);

        return boardList;
    }

    @Override
    public void modify(BoardVO board) throws SQLException {
        boardDAO.updateBoard(board);
        
    }

    @Override
    @Transactional
    public void regist(BoardVO board) throws SQLException {
        int bno = boardDAO.selectBoardSeqNext();
        board.setBno(bno);
        boardDAO.insertBoard(board);
        
    }

    @Override
    public void remove(int bno) throws SQLException {
        boardDAO.deleteBoard(bno);
        
    }

    

}
