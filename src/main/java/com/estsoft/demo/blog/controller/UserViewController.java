package com.estsoft.demo.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/login")
    public String logInView() {
        return "logIn";
    }

    @GetMapping("/signup")
    public String signUpView() {
        return "signUp";
    }
}
