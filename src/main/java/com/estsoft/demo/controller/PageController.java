package com.estsoft.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
public class PageController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {
        Person person = new Person();
        person.setId(1L);
        person.setName("Someone");
        person.setAge(20);
        person.setHobbies(Arrays.asList("drawing","travelling","jogging"));

        model.addAttribute("person", person);
        model.addAttribute("todayDate", LocalDateTime.now());

        return "examplePage";
    }
}
