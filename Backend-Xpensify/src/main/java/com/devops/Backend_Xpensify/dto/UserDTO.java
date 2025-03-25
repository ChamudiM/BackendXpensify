package com.devops.Backend_Xpensify.dto;

import com.devops.Backend_Xpensify.model.Users;

public class UserDTO {
    private String token;
    private Users user;

    public UserDTO(String token, Users user){
        this.token = token;
        this.user = user;
    }

    public String getToken(){
        return token;
    }

    public Users getUser(){
        return user;
    }
}
