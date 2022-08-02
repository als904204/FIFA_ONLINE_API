package com.toy.fifa.Controller.ExceptionController;


import com.toy.fifa.Config.ExceptionConfig.DuplicatedUsernameException;
import com.toy.fifa.Config.ExceptionConfig.DuplicatedUserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 예외처리 컨트롤러
@Log4j2
@RestControllerAdvice
public class ExceptionLabRestControllerAdvice {

    // 중복 아이디 예외
    @ExceptionHandler(DuplicatedUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String duplicatedUserNameExceptionHandler(DuplicatedUserException e) {
        log.error("DuplicatedUserException={}", e.getMessage());
        return e.getMessage();
    }

    // 중복 이메일 예외외
    @ExceptionHandler(DuplicatedUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String duplicatedEmailExceptionHandler(DuplicatedUsernameException e) {
        log.error("DuplicatedEmailException={}", e.getMessage());
        return e.getMessage();
    }
}
