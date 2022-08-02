package com.toy.fifa.Controller.ExceptionController;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Log4j2
public class CustomLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("login fail Handler");


        String errorMsg;
        if (e instanceof BadCredentialsException ){
            errorMsg="자격 증명 실패 : 아이디 또는 비밀번호가 맞지 않습니다.";

        }else if( e instanceof InternalAuthenticationServiceException) {
            errorMsg = " 시스템 문제로 내부 인증 관련 처리 요청오류";
        }else if (e instanceof UsernameNotFoundException){
            errorMsg="존재하지 않는 아이디 입니다.";
        }
        else{
            errorMsg="알 수 없는 이유로 로그인이 안되고 있습니다.";
        }
        errorMsg= URLEncoder.encode(errorMsg,"UTF-8");//한글 인코딩 깨지는 문제 방지
        setDefaultFailureUrl("/loginForm?error=true&exception=" + errorMsg);
        super.onAuthenticationFailure(request,response,e);
    }
}
