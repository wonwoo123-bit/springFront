package com.spring.application.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PdsVO {
    private int pno;
    private String title;
    private String writer;
    private String content;
    private int viewcnt;
    private Date regDate;
    private Date updateDate;

    private List<AttachVO> attachList;

}
