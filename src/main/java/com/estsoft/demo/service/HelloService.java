package com.estsoft.demo.service;

import org.springframework.stereotype.Service;

//Inversion of Control
@Service
public class HelloService {
    public static String sayHello() {
        return "Hello Spring!";
    }
}
