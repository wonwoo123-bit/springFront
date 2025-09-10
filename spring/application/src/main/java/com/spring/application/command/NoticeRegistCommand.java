package com.spring.application.command;

import com.spring.application.dto.NoticeVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NoticeRegistCommand {
    private String title;
    private String writer;
    private String content;

    public NoticeVO toNoticeVO(){
        NoticeVO notice = new NoticeVO();
        notice.setContent(content);
        notice.setWriter(writer);
        notice.setTitle(title);
        return notice;
    }


}
