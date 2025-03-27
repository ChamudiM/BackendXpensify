package com.devops.Backend_Xpensify.model;

import jakarta.persistence.*;
import lombok.ToString;


@ToString
@Entity
@Table(name = "auth")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;

    public int getId(){ return this.id; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
