package com.estsoft.demo.blog.controller;

import com.estsoft.demo.blog.dto.AddUserRequest;
import com.estsoft.demo.blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String signUp(@ModelAttribute AddUserRequest request) {
        userService.signUp(request);
        return "redirect:/login";   //redirect to log in page after sign up
    }

    @PostMapping("/logOutPage")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/login";   //redirect to log in page after log out
    }
}
