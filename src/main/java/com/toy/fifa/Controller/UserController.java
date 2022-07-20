package com.toy.fifa.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/join")
    public String userJoin() {
        return "/User/userJoin";
    }

    @GetMapping("/login")
    public String userLogin() {
        return "/User/userLogin";
    }
}
