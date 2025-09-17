package com.spring.application.command;

import com.josephoconnell.html.HTMLInputFilter;
import com.spring.application.dto.BoardVO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BoardRegistCommand {
    private String title;
    private String witer;
    private String content;

    public BoardVO toBoardVO() {
        BoardVO board = new BoardVO();
        board.setTitle(HTMLInputFilter.htmlSpecialChars(this.title));
        board.setWriter(witer);
        board.setContent(this.content);
        return board;
    }
}
