package com.spring.application.command;

import com.spring.application.dto.BoardVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BoardModifyCommand extends BoardRegistCommand {
    private int bno;
    
    @Override
    public BoardVO toBoardVO() {
        BoardVO board = super.toBoardVO();

        board.setBno(this.bno);

        return board;
    }
    
}
