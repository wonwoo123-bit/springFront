package com.spring.application.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

    @Autowired
    private SqlSession session;

    @Override
    public int countReply(int bno) throws SQLException {
        int cnt = session.selectOne("Reply-Mapper.countReply",bno);
        return cnt;
    }

    @Override
    public void deleteReply(int rno) throws SQLException {
        session.update("Reply-Mapper.deleteReply", rno);
    }

    @Override
    public void insertReply(ReplyVO reply) throws SQLException {
        session.update("Reply-Mapper.insertReply", reply);
    }

    @Override
    public List<ReplyVO> selectReplyListPage(int bno, PageMaker pageMaker) throws SQLException {
        int offset = pageMaker.getStartRow();
        int limit = pageMaker.getPerPageNum();
        RowBounds rowBounds = new RowBounds(offset, limit);

        return session.selectList("Reply-Mapper.selectReplyList", bno, rowBounds);
    }

    @Override
    public int selectReplySeqNextValue() throws SQLException {
        int rno = session.selectOne("Reply-Mapper.selectReplySeqNextValue");
        return rno;
    }

    @Override
    public void updateReply(ReplyVO reply) throws SQLException {
        session.update("Reply-Mapper.updateReply", reply);
        
    }

}
