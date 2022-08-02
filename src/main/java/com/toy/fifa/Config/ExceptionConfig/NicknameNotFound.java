package com.toy.fifa.Config.ExceptionConfig;

public class NicknameNotFound extends RuntimeException{
    public NicknameNotFound() {
        super("찾으시는 닉네임이 없습니다");
    }
}
