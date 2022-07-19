package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.DTO.Community_DTO.UserDto;
import com.toy.fifa.DTO.HttpStatus_DTO.ResponseDto;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Service.Community.CommunityUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class CommunityUserApiController {
    private final CommunityUserService userService;

    @PostMapping("/api/user/join")
    public ResponseDto<Integer> save(@RequestBody UserDto userDto) {
        log.info(userDto);
        log.info("save() 메서드 호출");
        userService.join(userDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
