package com.spring.application.command;

import com.spring.application.dto.PdsVO;

public class PdsModifyCommand extends PdsRegistCommand{
    private int pno;

    @Override
    public PdsVO toPdsVO() {
        
        PdsVO pds =  super.toPdsVO();
        pds.setPno(this.pno);

        return pds;
    }
    
}
