package com.models;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private String firstName;
    private String lastName;
    private List<Integer> grades;
}
