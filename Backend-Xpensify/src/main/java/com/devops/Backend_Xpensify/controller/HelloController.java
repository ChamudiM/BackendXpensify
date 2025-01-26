package com.devops.Backend_Xpensify.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @CrossOrigin
    @GetMapping("/auth/login")
    public String greet(){
        return "Hellow motherFu**er";
    }
}
