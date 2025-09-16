package com.spring.application.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BoardVO {
    private int bno;
    private String title;
    private String writer;
    private String content;
    private int viewcnt;
    private Date regDate;
    private Date updatedate;

    private int replycnt;
}
