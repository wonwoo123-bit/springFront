package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;


import com.spring.application.command.PageMaker;
import com.spring.application.dto.ReplyVO;

public interface ReplyService {

    List<ReplyVO> getReplyList(int bno, PageMaker pageMaker) throws SQLException;

    int getReplyListCount(int bno) throws SQLException;

    void registReply(ReplyVO reply) throws SQLException;

    void modifyReply(ReplyVO reply) throws SQLException;

    void removeReply(int rno) throws SQLException;


}
