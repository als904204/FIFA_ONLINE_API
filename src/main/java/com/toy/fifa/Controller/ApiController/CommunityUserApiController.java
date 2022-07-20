package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.DTO.ResponseUserDto;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Service.Community.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class CommunityUserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/user/join")
    public ResponseUserDto<Integer> userJoin(@RequestBody User user) {

        // 지워야 됨
        log.info("join() 호출={}", user);
        userService.join(user);
        return new ResponseUserDto<>(HttpStatus.OK.value(), 1);
    }
}
