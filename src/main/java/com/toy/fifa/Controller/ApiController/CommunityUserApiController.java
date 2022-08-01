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

         // 패스워드&패스워드 확인이 일치하지 않으면
         if (!userJoinForm.getPassword().equals(userJoinForm.getConfirmPassword())) {
         bindingResult.rejectValue("confirmPassword", "PWD and confirmPWD are different Error",
         "2개의 패스워드가 일치하지 않습니다.");
         return getResponseMessageResponseEntity(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST_ERROR_PASSWORD_NOT_CORRECT",HttpStatus.BAD_REQUEST);
         }



        // 필드값 오류 발생 시 (아이디가 2자리 이하거나 패스워드8자리 이하거나 패스워드가 일치하지 않을 시)
        if (bindingResult.hasErrors()) {
            bindingResult.rejectValue("nickname", "Nickname or password Error",
                    "닉네임 또는 패스워드 규약 에러(닉네임 : 2자리 이상 , 패스워드 8자리 이상)");
            return getResponseMessageResponseEntity(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST_ERROR_FILED_VALUE",HttpStatus.BAD_REQUEST);
        }

        userService.join(userJoinForm.getNickname(),userJoinForm.getEmail(),userJoinForm.getPassword());
        return getResponseMessageResponseEntity(HttpStatus.CREATED.value(), "Successful join ", HttpStatus.OK);

    }

    /**
     *
     * @param httpsStatus 응답 status
     * @param HttpMessage 응답 message
     * @param httpResponseStatus 서버한테 전송
     * @return  new ResponseEntity<>
     */
    private ResponseEntity<ResponseMessage> getResponseMessageResponseEntity(int httpsStatus, String HttpMessage, HttpStatus httpResponseStatus ) {
        responseMessage.setStatus(httpsStatus);
        responseMessage.setMessage(HttpMessage);
        return new ResponseEntity<>(responseMessage, httpHeaders, httpResponseStatus);
    }
}
