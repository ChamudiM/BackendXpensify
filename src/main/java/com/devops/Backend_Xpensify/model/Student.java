package com.devops.Backend_Xpensify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class Student {
    private int id;
    private String name;
    private int marks;
}
