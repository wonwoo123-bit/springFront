package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.application.command.PageMaker;
import com.spring.application.dao.ReplyDAO;
import com.spring.application.dto.ReplyVO;

@Service
public class ReplyServiceImpl  implements ReplyService{
    @Autowired 
    private ReplyDAO replyDAO;

    @Override
    public List<ReplyVO> getReplyList(int bno, PageMaker pageMaker) throws SQLException {
        List<ReplyVO> replyList = replyDAO.selectReplyListPage(bno,pageMaker);

        int cnt = replyDAO.countReply(bno);
        pageMaker.setTotalCount(cnt);
        
        return replyList;
    }

    @Override
    public int getReplyListCount(int bno) throws SQLException {
        int cnt = replyDAO.countReply(bno);
        return cnt;
    }

    @Override
    public void modifyReply(ReplyVO reply) throws SQLException {
        replyDAO.updateReply(reply);
        
    }

    @Override
    public void registReply(ReplyVO reply) throws SQLException {
        int rno = replyDAO.selectReplySeqNextValue();
        reply.setRno(rno);
        replyDAO.insertReply(reply);
        
    }

    @Override
    public void removeReply(int rno) throws SQLException {
        replyDAO.deleteReply(rno);
        
    }
    
}
