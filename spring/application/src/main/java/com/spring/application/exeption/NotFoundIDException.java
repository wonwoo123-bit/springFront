package com.spring.application.exeption;

public class NotFoundIDException extends Exception{
    public NotFoundIDException(){
        super("존재하지 않는 아이디 입니다.");
    }

}
