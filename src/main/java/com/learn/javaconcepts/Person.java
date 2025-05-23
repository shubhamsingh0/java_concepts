package com.learn.javaconcepts;

import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Data
public class Person extends Mammal{

    // Identification Properties
    private String name;
    private LocalDate dateOfBirth;
    private String gender;

    public Person() {
    }

    public Person(String name, LocalDate dateOfBirth, String gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    // behaviour or methods or functions
    public int calculateAge() {
        LocalDate today = LocalDate.now();
        if (Objects.nonNull(this.dateOfBirth)) {
            return Period.between(this.dateOfBirth, today).getYears();
        } else {
            return 0;
        }
    }
}
