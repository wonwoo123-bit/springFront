package com.spring.application.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.ReplyVO;
import com.spring.application.service.ReplyService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("/list")
    public ResponseEntity<Map<String,Object>> replyList(int bno, PageMaker pageMaker) {
        ResponseEntity<Map<String,Object>> entity = null;
        try{
            List<ReplyVO> replyList = replyService.getReplyList(bno, pageMaker);

            Map<String,Object> datMap = new HashMap<String, Object>();
            datMap.put("replyList", replyList);
            datMap.put("pageMaker", pageMaker);

            entity = new ResponseEntity<Map<String,Object>>(datMap,HttpStatus.OK);
        }catch(SQLException e){
            entity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entity;
    }
    
}
