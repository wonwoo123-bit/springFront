package com.spring.application.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.application.dto.AttachVO;
import com.spring.application.dto.PdsVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PdsRegistCommand {
    private String title;
    private String content;
    private String writer;
    private List<MultipartFile> uploadFile;

    public PdsVO topdsVO(){
        PdsVO pds = new PdsVO();
        pds.setContent(this.content);
        pds.setTitle(this.title);
        pds.setWriter(this.writer);

        return pds;
    }
}
