package com.example.java8to11.functionalInterface;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Foo4 {

    /* 메소드 레퍼런스 */
    public static void main(String[] args) {

        /* 람다로 구현하는 것이 아니라 기존에 있는 메소드를 참조시킴 */

        /* 특정 객체의 인스턴스 메소드 참조 */
        // 1. 기존 함수 인터페이스(람다식으로 구현)
        UnaryOperator<String> hi = (s) -> "hi " + s;

        // 2. 클래스의 static 메소드 사용
        UnaryOperator<String> hi2 = Greeting::hi;

         /* 생성자 참조 */
        // 1. 기본 생성자
        Supplier<Greeting> newGreeting = Greeting::new; // 생성X
        newGreeting.get(); // 생성O

        // 2. 매개변수가 있는 생성자
        Function<String, Greeting> newGreeting2 = Greeting::new;
        Greeting eunhee = newGreeting2.apply("eunhee");

        System.out.println(eunhee.getName());

        /* 임의 객체의 인스턴스 메소드 참조 */
        String[] names = {"keesun", "whiteship", "toby"};
        Arrays.sort(names, (String o1, String o2) -> 0); // 람다
        Arrays.sort(names, String::compareToIgnoreCase); // 메소드 레퍼런스
    }
}
