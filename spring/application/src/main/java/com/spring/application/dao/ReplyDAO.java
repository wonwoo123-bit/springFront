package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.ReplyVO;

public interface ReplyDAO {

    void insertReply(ReplyVO reply) throws SQLException;
    
    void updateReply(ReplyVO reply) throws SQLException;

    void deleteReply(int rno) throws SQLException;

    int selectReplySeqNextValue() throws SQLException;


    List<ReplyVO> selectReplyListPage(int bno, PageMaker pageMaker)throws SQLException;

    int countReply(int bno) throws SQLException;
}
