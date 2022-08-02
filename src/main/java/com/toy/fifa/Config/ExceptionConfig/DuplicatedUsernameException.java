package com.toy.fifa.Config.ExceptionConfig;

public class DuplicatedUsernameException extends RuntimeException{
    public DuplicatedUsernameException() {
        super("중복된 이메일입니다");
    }
}
