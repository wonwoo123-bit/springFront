package com.spring.application.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.application.command.PageMaker;
import com.spring.application.dto.AttachVO;
import com.spring.application.dto.PdsVO;

public interface PdsService {
    //전체
List<PdsVO> searchlist(PageMaker pageMaker)throws SQLException;
//생성
void regist(PdsVO pds)throws SQLException;
//읽기
void increaseViewCnt(int pno) throws SQLException;
PdsVO getPds(int pno) throws SQLException;
//수정
void modify(PdsVO pds)throws SQLException;
//삭제
void remove(int pno)throws SQLException;


//파일조회
AttachVO getAttachByAno(int ano)throws SQLException;
//파일삭제
void removeAttachByAno(int ano)throws SQLException;

}
