package com.example.java8to11.functionalInterface;

@FunctionalInterface
public interface ReturnSomething {

    int doIt(int num);

    // 1.
    static void printName() {
        System.out.println("eunhee");
    }

    // 2.
    default void printAge() {
        System.out.println("23");
    }

}
