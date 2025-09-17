package com.spring.application.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ReplyVO {
    private int rno;
    private int bno;
    private String replyer;
    private String replytext;
    private Date regdate;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    public Date getRdate(){
        return this.regdate;
    }




    // public long getRegdate(){
    //     return this.regdate.getTime();
    // }
    
}
