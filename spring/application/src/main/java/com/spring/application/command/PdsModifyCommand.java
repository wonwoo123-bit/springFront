package com.spring.application.command;

import com.spring.application.dto.PdsVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PdsModifyCommand extends PdsRegistCommand{
    private int pno;
    private int[] deleteFile;

    @Override
    public PdsVO topdsVO() {
        PdsVO pds = super.topdsVO();
        pds.setPno(this.pno);
        
        return pds;
    }

    
}
