package com.spring.application.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PageMaker {
    private int page = 1;
    private int perPageNum = 10;
    private String searchType = "";
    private String keyword = "";

    private int totalCount;
    private int startPage = 1;
    private int endPage = 1;
    private int realEndPage;
    private boolean prev;
    private boolean next;

    private int displayPageNum = 10;

    public int getStartRow(){
        return (this.page -1)* this.perPageNum;
    }

    private void calcData(){
        endPage = (int) (Math.ceil(page/ (double) displayPageNum)
                    * displayPageNum);
        
        startPage = (endPage - displayPageNum) + 1;

        realEndPage = (int) (Math.ceil(totalCount / (double) perPageNum));

        if(startPage < 0){
            startPage =1;
        }
        if(endPage > realEndPage){
            endPage = realEndPage;
        }

        prev = startPage ==1 ? false : true;
        next = endPage < realEndPage ? true : false;

    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;

        calcData();
    }


}
