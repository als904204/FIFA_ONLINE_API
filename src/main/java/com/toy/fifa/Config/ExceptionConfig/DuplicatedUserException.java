package com.toy.fifa.Config.ExceptionConfig;

public class DuplicatedUserException extends RuntimeException{
    public DuplicatedUserException() {
        super("중복된 닉네임입니다");
    }
}
