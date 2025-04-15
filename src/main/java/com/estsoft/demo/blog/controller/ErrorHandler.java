package com.estsoft.demo.blog.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorHandler {
    private String errorCode;
    private String errorMessage;
}
