package com.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Person {
    private String firstName;
    private String secondName;
    private String address;
    private int age;
    private Date birthDate;
    private String email;
    private List<String> phoneNumber;

}
