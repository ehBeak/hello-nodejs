package com.example.java8to11.functionalInterface;

import java.util.function.*;

public class Foo3 {

    /* 람다 표현식 */
    public static void main(String[] args) {

        Supplier<Integer> get10 = () -> {
            return 10;
        };

        BiFunction<Integer, Integer, Integer> plus = (num1, num2) -> {
            int res = num1 + num2;
            return res;
        };

        BinaryOperator<Integer> plus2 = (num1, num2) -> num1 + num2;


        Foo3 foo3 = new Foo3();


    }

    /* 로컬 클래스, 익명 클래스, 람다의 스코프, effective final */
    private void run() {

        // final int baseNumber
        int baseNumber = 10; // effective final

        // 로컬 클래스 - baseNumber 참조 가능, shadowing 가능
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 선언한 11출력
            }
        }

        // 익명 클래스 - baseNumber 참조 가능, shadowing 가능
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber); // 전달되는 값 출력
            }
        };

        // 람다 - baseNumber 참조 가능, shadowing 불가
        IntConsumer printInt = (i) -> {
            // int baseNumber = 11; 오류
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);

//        baseNumber++; => 더이상 effective final이 아니니까 컴파일 오류

//        shadowing이 가능한 이유
//        -> 클래스는 별도의 scope이기 때문
//        -> 람다는 Foo3클래스와 scope이 같다
    }
}
