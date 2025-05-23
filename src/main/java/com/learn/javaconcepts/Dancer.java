package com.learn.javaconcepts;

import java.time.LocalDate;

public class Dancer extends Person {
    private boolean isDancer;
    private String  dancingStyle;

    public Dancer() {}

    public Dancer(boolean isDancer, String dancingStyle) {
        this.isDancer = isDancer;
        this.dancingStyle = dancingStyle;
    }

    public Dancer(String name, LocalDate dateOfBirth, String gender, boolean isDancer, String dancingStyle) {
        super(name, dateOfBirth, gender);
        this.isDancer = isDancer;
        this.dancingStyle = dancingStyle;
    }

    public void perform() {
        System.out.println("dance  like a top");
    }

    public static void main(String[] args) {

        Dancer naruto = new Dancer("Naruto", LocalDate.of(1994, 10,12),"male",false, "Western");

        Person sauske = new Dancer("Sauske", LocalDate.of(1997, 12,11),"female",true, "Hip-Hop");
    }

}
