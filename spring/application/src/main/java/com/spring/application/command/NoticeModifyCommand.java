package com.spring.application.command;

import com.spring.application.dto.NoticeVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeModifyCommand extends NoticeRegistCommand {
    private int nno;
    
    @Override
    public NoticeVO toNoticeVO() {
        NoticeVO notice = super.toNoticeVO();

        notice.setNno(this.nno);

        return notice;
    }
    
}
