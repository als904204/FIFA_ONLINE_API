package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.Config.ExceptionConfig.DuplicatedUserException;
import com.toy.fifa.DTO.ResponseMessage;
import com.toy.fifa.Entity.UserJoinForm;
import com.toy.fifa.Service.Community.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.charset.Charset;

@Log4j2
@RequiredArgsConstructor
@RestController
public class CommunityUserApiController {

    private final UserService userService;
    private final ResponseMessage responseMessage;
    private final HttpHeaders  httpHeaders;

    // TODO : URL 통합 해야 됨
    //    GET /members/show/1     (x)
    //    GET /members/1          (o)
    @PostMapping("/api/v1/user/join")
    public ResponseEntity<ResponseMessage> userJoin(@RequestBody @Valid UserJoinForm userJoinForm, BindingResult bindingResult) {


        log.info("ResultBinding={}",bindingResult);

        httpHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        responseMessage.setData(userJoinForm);

        // 패스워드가 일치하지 않으면 안됨
        /**
             if (!userJoinForm.getPassword().equals(userJoinForm.getConfirmPassword())) {
             bindingResult.rejectValue("confirmPassword", "PWD and confirmPWD are different Error",
             "2개의 패스워드가 일치하지 않습니다.");
             }
         * **/


        // 필드값 오류 발생 시 (아이디가 6자리 이하거나 패스워드8자리 이하거나 패스워드가 일치하지 않을 시)
        if (bindingResult.hasErrors()) {
            responseMessage.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMessage.setMessage("BAD_REQUEST_ERROR");
            throw new IllegalArgumentException("잘못된 요청입니다");
        }



        responseMessage.setStatus(HttpStatus.CREATED.value());
        responseMessage.setMessage("Successful join ");
        userService.join(userJoinForm.getUsername(),userJoinForm.getEmail(),userJoinForm.getPassword());
        return new ResponseEntity<>(responseMessage, httpHeaders, HttpStatus.OK);
    }
}
