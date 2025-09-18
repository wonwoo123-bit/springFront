package com.spring.application.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.application.dto.PdsVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PdsRegistCommand {
    private String title;
    private String writer;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public PdsVO toPdsVO(){
        PdsVO pds = new PdsVO();

        pds.setTitle(HTMLInputFilter.htmlSpecialChars(this.title));
        pds.setWriter(this.writer);
        pds.setContent(this.content);
        pds.setStartDate(this.startDate);
        pds.setEndDate(this.endDate);

        return pds;
    }

}
