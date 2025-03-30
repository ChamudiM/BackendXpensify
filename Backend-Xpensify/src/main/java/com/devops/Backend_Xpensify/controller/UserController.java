package com.devops.Backend_Xpensify.controller;

import com.devops.Backend_Xpensify.dto.UserDTO;
import com.devops.Backend_Xpensify.model.Users;
import com.devops.Backend_Xpensify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Xpensify!";
    }

    @PostMapping("auth/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }

    @PostMapping("auth/login")
    public UserDTO login(@RequestBody Users user){
        return service.verify(user);
    }
}
