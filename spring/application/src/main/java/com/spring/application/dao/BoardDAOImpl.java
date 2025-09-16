package com.spring.application.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void deleteBoard(int bno) throws SQLException {
        sqlSession.delete("Board-Mappe.deleteBoard",bno);
        
    }

    @Override
    public void increaseViewCount(int bno) throws SQLException {
        sqlSession.update("Board-Mapper.increaseViewCount", bno);
        
    }

    @Override
    public void insertBoard(BoardVO board) throws SQLException {
        sqlSession.insert("Board-Mapper.insertBoard",board);
        
    }

    @Override
    public BoardVO selectBoardByBno(int bno) throws SQLException {
        BoardVO board = sqlSession.selectOne("Board-Mapper.selectBoardByBno",bno);
        return board;
    }

    @Override
    public int selectBoardSeqNext() throws SQLException {
        int bno = sqlSession.selectOne("Board-Mapper.selectBoardSeqNext");
        return bno;
    }

    @Override
    public int selectSearchBoardListCount(PageMaker pageMaker) throws SQLException {
        int cnt = sqlSession.selectOne("Board-Mapper.selectSearchBoardListCount",pageMaker);
        return cnt;
    }

    @Override
    public List<BoardVO> selectSearchBoardVOList(PageMaker pageMaker) throws SQLException {
        int startRow = pageMaker.getStartRow();
        int endRow = startRow + pageMaker.getPerPageNum();

        Map<String, Object> dataParam = new HashMap<String,Object>();
        dataParam.put("startRow", startRow);
        dataParam.put("endRow", endRow);
        dataParam.put("searchType", pageMaker.getSearchType());
        dataParam.put("keyword", pageMaker.getKeyword());

        List<BoardVO> boardList = sqlSession.selectList("Board-Mapper.selectSearchBoardVOList",dataParam);
        return boardList;
    }

    @Override
    public void updateBoard(BoardVO board) throws SQLException {
        sqlSession.update("Board-Mapper.updateBoard",board);
        
    }

    
}
