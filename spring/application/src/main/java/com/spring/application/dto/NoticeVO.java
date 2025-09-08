package com.spring.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NoticeVO {
    private int nno;
    private String title = "";
    private String writer;
    private String content = "";
    private int viewcnt = 0;
}
