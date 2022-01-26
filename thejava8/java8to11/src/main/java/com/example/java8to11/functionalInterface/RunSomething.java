package com.example.java8to11.functionalInterface;

@FunctionalInterface // 함수형 인터페이스 정의한 경우 어노테이션 쓰기.
public interface RunSomething {
    /* '추상' 메소드가 하나만 있으면 함수형 인터페이스 */
    void doIt(); // == abstract void doIt

    /* java8부터 ...
    *  1. static 메소드를 public 키워드를 지울 수 있다
    *  2. default 메소드를 정의 가능 */

    // 1.
    static void printName() {
        System.out.println("eunhee");
    }

    // 2.
    default void printAge() {
        System.out.println("23");
    }


}
