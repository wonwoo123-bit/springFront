package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.PdsVO;

public interface PdsService {
    //전체
List<PdsVO> list(PageMaker pageMaker)throws SQLException;
//상세
PdsVO detail(int pno)throws SQLException;
//등록
void regist(PdsVO pds)throws SQLException;
//수정
PdsVO getPds(int pno) throws SQLException;
void modify(PdsVO pds)throws SQLException;
//삭제
void remove(int pno)throws SQLException;
}
