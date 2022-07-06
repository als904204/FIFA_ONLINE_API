package com.toy.fifa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FIFAController {

    @GetMapping("/")
    public String index() {
        return "/FIFA_main";
    }

    @GetMapping("/test")
    public String test() {
        return "/test/test";
    }

    @GetMapping("/test-div")
    public String test_div() {
        return "/test/test-div";
    }
}
