package com.spring.application.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttachVO {
    private int ano;
    private String uploadPath;
    private String fileName;
    private String fileType;
    private int pno;
    private String attacher;
    private Date regDate;
}
