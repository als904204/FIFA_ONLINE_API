package com.toy.fifa.Controller;

import com.toy.fifa.Entity.DTO.UserInfoResponseDto;
import com.toy.fifa.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class FIFAController {

    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user/info/{nickname}")
    public String userInfo(@PathVariable String nickname, Model model) {
        UserInfoResponseDto userInfoResponseDto = userService.findUserInfoById(nickname);
        model.addAttribute("userInfo",userInfoResponseDto);
       return "/FIFA/user-info";
    }




}
