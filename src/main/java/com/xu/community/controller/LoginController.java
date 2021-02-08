package com.xu.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(path = "/register")
    public String getRegisterPage(){
        return "/site/register";

    }
}
