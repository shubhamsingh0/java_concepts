package com.learn.javaconcepts;

import java.time.LocalDate;

public class Singer extends Person {

    private boolean isSinger;
    private String singingStyle;

    public Singer() {
    }
    public Singer(boolean isSinger, String singingStyle) {
        this.isSinger = isSinger;
        this.singingStyle = singingStyle;
    }

    public Singer(String name, LocalDate dateOfBirth, String gender, boolean isSinger, String singingStyle) {
        super(name, dateOfBirth, gender);
        this.isSinger = isSinger;
        this.singingStyle = singingStyle;
    }

    public static void main(String[] args) {

        Singer itachi = new Singer("Itachi", LocalDate.of(1994, 10,12),"male",false, "none");

        // enables polymorphism, this allows an object reference to behave differently based
        // on its actual type. At runtime Java looks at the actual object type (Singer)
        // not the reference type(Person) and will call overriden methods in singer
        // Reference object Person, Actual Object Person => Person method call
        // Reference object Person, Actual Object Singer => Singer method call
        // This is common in Upcasting at time of Dynamic method Dispatch or Runtime polymorphism


        Person hinata = new Singer("Hinata", LocalDate.of(1997, 12,11),"female",true, "classical");
        Singer singerHinata = (Singer) hinata; // Downcasting
    }

    public void perform() {
        System.out.println("Parent Class: Sing like a bird");
    }

    public boolean isSinger() {
        return isSinger;
    }

    public void setSinger(boolean singer) {
        isSinger = singer;
    }

    public String getSingingStyle() {
        return singingStyle;
    }


    public void setSingingStyle(String singingStyle) {
        this.singingStyle = singingStyle;
    }
}
