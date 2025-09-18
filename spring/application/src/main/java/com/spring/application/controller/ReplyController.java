package com.spring.application.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.application.command.PageMaker;
import com.spring.application.dto.ReplyVO;
import com.spring.application.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService service;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> replyList(int bno, PageMaker pageMaker) {
        ResponseEntity<Map<String, Object>> entity = null;
        try {
            List<ReplyVO> replyList = service.getReplyList(bno, pageMaker);

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("replyList", replyList);
            dataMap.put("pageMaker", pageMaker);

            entity = new ResponseEntity<Map<String, Object>>(dataMap, HttpStatus.OK);

        } catch (SQLException e) {
            entity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @PostMapping("/regist")
    public ResponseEntity<String> regist(@RequestBody ReplyVO reply) {
        ResponseEntity<String> entity = null;

        reply.setReplytext(HTMLInputFilter.htmlSpecialChars(reply.getReplytext()));

        try {
            service.registReply(reply);

            int totalCount = service.getReplyListCount(reply.getBno());
            int perPageNum = new PageMaker().getPerPageNum();
            String pageNum = "" + (int) Math.ceil(totalCount / (double) perPageNum);

            entity = new ResponseEntity<String>(pageNum, HttpStatus.OK);

        } catch (SQLException e) {
            entity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }

    @PatchMapping("/modify")
    public ResponseEntity<String> modify(@RequestBody ReplyVO reply) {
        ResponseEntity<String> entity = null;

        reply.setReplytext(HTMLInputFilter.htmlSpecialChars(reply.getReplytext()));

        try {
            service.modifyReply(reply);
            entity = new ResponseEntity<String>(HttpStatus.OK);
        } catch (SQLException e) {
            entity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return entity;
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<String> remove(int rno, int bno, int page) {
        ResponseEntity<String> entity = null;

        try {
            service.removeReply(rno);
            int realEndPage = -1;
            int totalCount = service.getReplyListCount(bno);
            int perPageNum = new PageMaker().getPerPageNum();
            realEndPage = (int) Math.ceil(totalCount / (double) perPageNum);

            String pageNum = page + "";
            if (page > realEndPage) {
                pageNum = "" + realEndPage;
            }
            entity = new ResponseEntity<String>(pageNum, HttpStatus.OK);
        } catch (SQLException e) {
            entity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }
}